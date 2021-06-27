package com.AssetArrange.CryptoAggregator.Core.dto;

import com.google.gson.Gson;
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
public class UserAccountData {
    private String product_id;
    private BigDecimal exchange_volume;
    private BigDecimal volume;
    private String record_at;

    private static final Gson GSON = new Gson();

    public String toString() {
        return GSON.toJson(this);
    }
}
