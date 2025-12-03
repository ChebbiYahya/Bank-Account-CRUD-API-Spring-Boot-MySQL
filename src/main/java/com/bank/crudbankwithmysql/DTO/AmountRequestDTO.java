package com.bank.crudbankwithmysql.DTO;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmountRequestDTO {
    private BigDecimal amount;
}
