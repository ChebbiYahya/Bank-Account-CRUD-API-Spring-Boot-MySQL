package com.bank.crudbankwithmysql.interfaces;

import com.bank.crudbankwithmysql.entities.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByAccountId(Long accountId);
}
