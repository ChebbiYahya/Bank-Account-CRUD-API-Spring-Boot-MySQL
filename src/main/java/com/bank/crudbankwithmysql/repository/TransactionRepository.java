package com.bank.crudbankwithmysql.repository;

import com.bank.crudbankwithmysql.entities.BankAccount;
import com.bank.crudbankwithmysql.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByBankAccountIdOrderByCreatedAtDesc(Long accountId);
}
