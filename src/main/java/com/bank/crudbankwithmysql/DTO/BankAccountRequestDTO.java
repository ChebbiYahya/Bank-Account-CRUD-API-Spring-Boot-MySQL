package com.bank.crudbankwithmysql.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountRequestDTO {
    private String iban;
    private String status;
    private String accountType; // CHECKING, SAVINGS...
    private Long customerId;
}
