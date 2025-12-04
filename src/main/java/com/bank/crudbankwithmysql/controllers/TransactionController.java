package com.bank.crudbankwithmysql.controllers;

import com.bank.crudbankwithmysql.DTO.TransactionResponseDTO;
import com.bank.crudbankwithmysql.entities.Transaction;
import com.bank.crudbankwithmysql.interfaces.TransactionService;
import com.bank.crudbankwithmysql.mappers.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts/{accountId}/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping
    public List<TransactionResponseDTO> getTransactions(@PathVariable Long accountId) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
        return transactions.stream()
                .map(transactionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
