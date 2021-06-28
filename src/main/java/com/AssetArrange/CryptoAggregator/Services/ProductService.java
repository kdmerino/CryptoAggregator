package com.AssetArrange.CryptoAggregator.Services;

import com.AssetArrange.CryptoAggregator.Constants.Endpoints;
import com.AssetArrange.CryptoAggregator.Core.dto.Ticker;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class ProductService {

    private final ICoinbaseProxy proxy;

    public ProductService(final ICoinbaseProxy proxy) {
        this.proxy = proxy;
    }

    public List<Ticker> getTickers(final String productId) {
        final String uri = Endpoints.PRODUCTS_FS + productId + Endpoints.TICKER;
        return proxy.getAsList(uri, new ParameterizedTypeReference<>() {});
    }
}
