package com.bank.crudbankwithmysql.mappers;

import com.bank.crudbankwithmysql.DTO.BankAccountRequestDTO;
import com.bank.crudbankwithmysql.DTO.BankAccountResponseDTO;
import com.bank.crudbankwithmysql.entities.BankAccount;
import com.bank.crudbankwithmysql.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccount toEntity(BankAccountRequestDTO dto) {
        return BankAccount.builder()
                .iban(dto.getIban())
                .status(dto.getStatus())
                .accountType(dto.getAccountType())
                .build();
    }

    public BankAccountResponseDTO toResponseDTO(BankAccount account) {
        Customer customer = account.getCustomer();
        String fullName = customer != null
                ? customer.getFirstName() + " " + customer.getLastName()
                : null;

        return BankAccountResponseDTO.builder()
                .id(account.getId())
                .iban(account.getIban())
                .balance(account.getBalance())
                .status(account.getStatus())
                .accountType(account.getAccountType())
                .createdAt(account.getCreatedAt())
                .customerId(customer != null ? customer.getId() : null)
                .customerFullName(fullName)
                .build();
    }
}
