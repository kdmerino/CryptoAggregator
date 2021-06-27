package com.AssetArrange.CryptoAggregator.Core.dto;

import com.AssetArrange.CryptoAggregator.Core.Detail;
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
public class AccountHistory {
    private String id;
    private String created_at;
    private BigDecimal amount;
    private BigDecimal balance;
    private String type;
    private Detail details;

    private static final Gson GSON = new Gson();

    public String toString() {
        return GSON.toJson(this);
    }
}
