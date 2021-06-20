package com.AssetArrange.CryptoAggregator.Core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Detail {
    private String order_id;
    private Integer trade_id;
    private String product_id;
}
