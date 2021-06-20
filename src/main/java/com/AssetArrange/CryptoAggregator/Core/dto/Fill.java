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
public class Fill {
    private Integer trade_id;
    private String product_id;
    private BigDecimal size;
    private String order_id;
    private String created_at;
    private String liquidity;
    private BigDecimal fee;
    private Boolean settled;
    private String side;
}
