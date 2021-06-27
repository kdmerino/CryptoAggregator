package com.AssetArrange.CryptoAggregator.init;

import com.AssetArrange.CryptoAggregator.Proxy.CoinbaseProxy;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import com.AssetArrange.CryptoAggregator.init.coinbasePro.CoinbaseProSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class initSupport {

    /**
     *  Creates an instance of ICoinbaseProxy.
     */
    @Bean("coinbaseProxy")
    public ICoinbaseProxy getCoinbaseProxy(final CoinbaseProSettings coinbaseProSettings) {
        return new CoinbaseProxy(coinbaseProSettings);
    }
}
