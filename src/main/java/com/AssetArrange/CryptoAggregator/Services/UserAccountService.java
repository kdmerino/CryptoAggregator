package com.AssetArrange.CryptoAggregator.Services;

import com.AssetArrange.CryptoAggregator.Constants.Endpoints;
import com.AssetArrange.CryptoAggregator.Core.dto.UserAccountData;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class UserAccountService {
    private final ICoinbaseProxy exchange;

    public UserAccountService(final ICoinbaseProxy exchange) {
        this.exchange = exchange;
    }

    /**
     * Returns the 30 day trailing volume information from all accounts.
     * @return UserAccountData
     */
    public List<UserAccountData> getTrailingVolume() {
        return exchange.getAsList(Endpoints.USER_ACCOUNT, new ParameterizedTypeReference<>() {});
    }
}
