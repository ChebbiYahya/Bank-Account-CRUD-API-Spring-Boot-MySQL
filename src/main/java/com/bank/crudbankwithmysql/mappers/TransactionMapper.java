package com.bank.crudbankwithmysql.mappers;

import com.bank.crudbankwithmysql.DTO.TransactionResponseDTO;
import com.bank.crudbankwithmysql.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponseDTO toResponseDTO(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .type(transaction.getType().name())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .createdAt(transaction.getCreatedAt())
                .balanceAfterOperation(transaction.getBalanceAfterOperation())
                .build();
    }
}
