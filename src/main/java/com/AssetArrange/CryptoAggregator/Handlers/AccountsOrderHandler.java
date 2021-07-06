package com.AssetArrange.CryptoAggregator.Handlers;

import com.AssetArrange.CryptoAggregator.Core.Detail;
import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Core.dto.AccountHistory;
import com.AssetArrange.CryptoAggregator.Services.AccountService;
import com.AssetArrange.CryptoAggregator.Services.OrderService;

import java.util.stream.Collectors;

public class AccountsOrderHandler implements IHandler {
    private static final String MATCH = "match";

    public boolean execute(IContext iContext) {
        Context context = (Context) iContext;
        AccountService accountService = new AccountService(context.getProxy());
        OrderService orderService = new OrderService(context.getProxy());
        // List<Asset> : Asset -> id -> List<AccountHistory> -> List<Order> (consumed by Asset)
        context.getAssetMap().values()
                .forEach(asset -> asset.addOrders(
                        accountService.getAccountHistory(asset.getId()).stream()
                                .filter(history -> history.getType().equalsIgnoreCase(MATCH))
                                .map(AccountHistory::getDetails).map(Detail::getOrder_id)
                                .distinct()
                                .map(orderService::getOrder)
                                .collect(Collectors.toList())));

        return !context.getAssetMap().isEmpty();
    }
}
