package com.bank.crudbankwithmysql.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDTO {
    private Long id;
    private String type; // DEPOSIT / WITHDRAW
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private BigDecimal balanceAfterOperation;
}
