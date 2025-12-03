package com.bank.crudbankwithmysql.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountRequestDTO {
    private String ownerName;
    private String iban;
    private String status;
}
