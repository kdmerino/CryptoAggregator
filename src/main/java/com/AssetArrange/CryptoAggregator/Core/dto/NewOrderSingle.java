package com.AssetArrange.CryptoAggregator.Core.dto;

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
public class NewOrderSingle {
    private String client_oid;
    private String type;
    private String side;
    private String product_id;
    private String stp;
    private String funds;
}
