package com.AssetArrange.CryptoAggregator.Model;

import com.AssetArrange.CryptoAggregator.Core.dto.Account;
import com.AssetArrange.CryptoAggregator.Core.dto.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Asset {
    private final String id;
    private final String currency;
    private final List<Order> orders;
    private final BigDecimal volume;

    private BigDecimal price;
    private BigDecimal pending;
    private BigDecimal balance;

    public Asset(Builder builder) {
        this.id = builder.id;
        this.currency = builder.currency;
        this.balance = builder.balance;
        this.volume = builder.volume;
        this.pending = builder.pending;
        this.orders = builder.orders;
    }

    public String getId() {
        return this.id;
    }

    public String getCurrency() {
        return this.currency;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public BigDecimal getVolume() {
        return this.volume;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getPending() {
        return this.pending;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setPending(BigDecimal pending) {
        this.pending = pending;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setPrice(Double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void addOrders(List<Order> orders) {
        this.orders.addAll(orders);
    }

    public static class Builder {
        private String id;
        private String currency;
        private BigDecimal volume;
        private BigDecimal balance;
        private BigDecimal pending;
        private BigDecimal price;
        private List<Order> orders;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setVolume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }

        public Builder setBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder setPending(BigDecimal pending) {
            this.pending = pending;
            return this;
        }

        public Builder setOrders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder addOrders(List<Order> orders) {
            if (this.orders != null) {
                this.orders.addAll(orders);
            } else {
                this.orders = orders;
            }
            return this;
        }

        public Builder fromAccount(Account account) {
            this.id = account.getId();
            this.currency = account.getCurrency();
            this.volume = account.getBalance();
            this.orders = new ArrayList<>();
            return this;
        }

        public Asset build() {
            if (this.orders == null) {
                this.orders = new ArrayList<>();
            }
            return new Asset(this);
        }
    }

}