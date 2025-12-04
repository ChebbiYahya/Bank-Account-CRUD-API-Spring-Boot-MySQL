package com.bank.crudbankwithmysql.services;

import com.bank.crudbankwithmysql.entities.BankAccount;
import com.bank.crudbankwithmysql.entities.Customer;
import com.bank.crudbankwithmysql.entities.Transaction;
import com.bank.crudbankwithmysql.entities.TransactionType;
import com.bank.crudbankwithmysql.exceptions.AccountNotFoundException;
import com.bank.crudbankwithmysql.exceptions.CustomerNotFoundException;
import com.bank.crudbankwithmysql.exceptions.InsufficientBalanceException;
import com.bank.crudbankwithmysql.interfaces.BankAccountService;
import com.bank.crudbankwithmysql.repository.BankAccountRepository;
import com.bank.crudbankwithmysql.repository.CustomerRepository;
import com.bank.crudbankwithmysql.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getAccountById(Long id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
    }

    @Override
    public BankAccount createAccount(Long customerId, BankAccount account) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        if (account.getCreatedAt() == null) {
            account.setCreatedAt(LocalDateTime.now());
        }
        if (account.getBalance() == null) {
            account.setBalance(BigDecimal.ZERO);
        }
        if (account.getStatus() == null) {
            account.setStatus("ACTIVE");
        }
        if (account.getAccountType() == null) {
            account.setAccountType("CHECKING");
        }

        account.setId(null);
        account.setCustomer(customer);

        return bankAccountRepository.save(account);
    }

    @Override
    public BankAccount updateAccount(Long id, BankAccount account) {
        BankAccount existing = getAccountById(id);
        existing.setIban(account.getIban());
        existing.setStatus(account.getStatus());
        existing.setAccountType(account.getAccountType());
        return bankAccountRepository.save(existing);
    }

    @Override
    public void deleteAccount(Long id) {
        BankAccount existing = getAccountById(id);
        bankAccountRepository.delete(existing);
    }

    @Override
    public BankAccount deposit(Long id, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        BankAccount account = getAccountById(id);
        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);

        BankAccount saved = bankAccountRepository.save(account);

        createTransaction(saved, amount, TransactionType.DEPOSIT, "Deposit");

        return saved;
    }

    @Override
    public BankAccount withdraw(Long id, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        BankAccount account = getAccountById(id);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Not enough balance to withdraw");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);

        BankAccount saved = bankAccountRepository.save(account);

        createTransaction(saved, amount, TransactionType.WITHDRAW, "Withdraw");

        return saved;
    }

    private void createTransaction(BankAccount account,
                                   BigDecimal amount,
                                   TransactionType type,
                                   String description) {

        Transaction transaction = Transaction.builder()
                .amount(amount)
                .type(type)
                .description(description)
                .createdAt(LocalDateTime.now())
                .balanceAfterOperation(account.getBalance())
                .bankAccount(account)
                .build();

        transactionRepository.save(transaction);
    }
}
