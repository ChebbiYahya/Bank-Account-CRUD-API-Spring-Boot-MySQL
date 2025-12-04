package com.bank.crudbankwithmysql.services;

import com.bank.crudbankwithmysql.entities.Transaction;
import com.bank.crudbankwithmysql.interfaces.TransactionService;
import com.bank.crudbankwithmysql.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServicesImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByBankAccountIdOrderByCreatedAtDesc(accountId);

    }
}