package com.AssetArrange.CryptoAggregator.Core.context;

import com.AssetArrange.CryptoAggregator.Core.dto.Account;
import com.AssetArrange.CryptoAggregator.Core.dto.AccountHistory;
import com.AssetArrange.CryptoAggregator.Core.dto.Order;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Context implements IContext {
    private ICoinbaseProxy proxy;
    private List<Account> accountList;
    private List<AccountHistory> accountHistories;
    private List<Order> matchOrders;
}
