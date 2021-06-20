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
public class Hold {
    private String id;
    private String account_id;
    private String created_at;
    private String update_at;
    private BigDecimal amount;
    private String type;
    private String ref;
}
