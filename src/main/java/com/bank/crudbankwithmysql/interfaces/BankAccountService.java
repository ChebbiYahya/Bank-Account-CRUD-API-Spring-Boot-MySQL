package com.bank.crudbankwithmysql.interfaces;

import com.bank.crudbankwithmysql.entities.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountService {
    List<BankAccount> getAllAccounts();

    BankAccount getAccountById(Long id);

    BankAccount createAccount(BankAccount account);

    BankAccount updateAccount(Long id, BankAccount account);

    void deleteAccount(Long id);

    BankAccount deposit(Long id, BigDecimal amount);

    BankAccount withdraw(Long id, BigDecimal amount);
}
