package com.AssetArrange.CryptoAggregator.Services;

import com.AssetArrange.CryptoAggregator.Constants.Endpoints;
import com.AssetArrange.CryptoAggregator.Core.dto.Fill;
import com.AssetArrange.CryptoAggregator.Core.dto.Hold;
import com.AssetArrange.CryptoAggregator.Core.dto.NewOrderSingle;
import com.AssetArrange.CryptoAggregator.Core.dto.Order;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Arrays;
import java.util.List;

public class OrderService {
    private final ICoinbaseProxy exchange;

    public OrderService(final ICoinbaseProxy exchange) {
        this.exchange = exchange;
    }

    public List<Hold> getHolds(String accountId) {
        final String uri = Endpoints.ORDERS_FS + accountId + Endpoints.HOLDS;
        return exchange.getAsList(uri, new ParameterizedTypeReference<>() {});
    }

    public List<Hold> getOpenOrders(String accountId) {
        final String uri = Endpoints.ORDERS_FS + accountId + Endpoints.ORDERS;
        return exchange.getAsList(uri, new ParameterizedTypeReference<>() {});
    }

    public Order getOrder(String orderId) {
        return exchange.get(Endpoints.ORDERS_FS, new ParameterizedTypeReference<>() {});
    }

    public List<Hold> createOrder(NewOrderSingle order) {
        return exchange.post(Endpoints.ORDERS, new ParameterizedTypeReference<>() {}, order);
    }

    public List<Hold> cancelOrder(String orderId) {
        final String uri = Endpoints.ORDERS_FS + orderId;
        return exchange.delete(uri, new ParameterizedTypeReference<>() {});
    }

    public List<Order> getOpenOrders() {
        return exchange.getAsList(Endpoints.ORDERS, new ParameterizedTypeReference<>() {});
    }

    /* This method is causing a compiler, via build failure
     * java: Compilation failed: internal java compiler error

    public List<Order> cancelAllOpenOrders() {
        return Arrays.asList(exchange.delete(Endpoints.ORDERS, new ParameterizedTypeReference<>() {}));
    }

     */

    public List<Fill> getFillsByProductId(String product_id, int resultLimit) {
        final String uri = Endpoints.FILLS + "?product_id=" + product_id + "&limit=" + resultLimit;
        return exchange.getAsList(uri, new ParameterizedTypeReference<>() {});
    }

    public List<Fill> getFillByOrderId(String order_id, int resultLimit) {
        final String uri = Endpoints.FILLS + "?order_id=" + order_id + "&limit=" + resultLimit;
        return exchange.getAsList(uri, new ParameterizedTypeReference<>() {});
    }
}
