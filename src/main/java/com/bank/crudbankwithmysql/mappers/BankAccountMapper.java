package com.bank.crudbankwithmysql.mappers;

import com.bank.crudbankwithmysql.DTO.BankAccountRequestDTO;
import com.bank.crudbankwithmysql.DTO.BankAccountResponseDTO;
import com.bank.crudbankwithmysql.entities.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccount toEntity(BankAccountRequestDTO dto) {
        return BankAccount.builder()
                .ownerName(dto.getOwnerName())
                .iban(dto.getIban())
                .status(dto.getStatus())
                .build();
    }

    public BankAccountResponseDTO toResponseDTO(BankAccount account) {
        return BankAccountResponseDTO.builder()
                .id(account.getId())
                .ownerName(account.getOwnerName())
                .iban(account.getIban())
                .balance(account.getBalance())
                .status(account.getStatus())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
