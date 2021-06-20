package com.AssetArrange.CryptoAggregator.Core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String id;
    private String currency;
    private BigDecimal balance;
    private BigDecimal available;
    private BigDecimal hold;
    private String profile_id;
}
