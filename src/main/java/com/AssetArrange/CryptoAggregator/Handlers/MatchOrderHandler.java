package com.AssetArrange.CryptoAggregator.Handlers;

import com.AssetArrange.CryptoAggregator.Core.Detail;
import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Core.dto.AccountHistory;
import com.AssetArrange.CryptoAggregator.Services.OrderService;

import java.util.stream.Collectors;

public class MatchOrderHandler implements IHandler {
    private static final String MATCH = "match";

    public boolean execute(IContext iContext) {
        Context context = (Context) iContext;
        OrderService orderService = new OrderService(context.getProxy());
        // List<AccountHistory> -> List<type match: AccountHistory> -> List<Details> -> List<Order_ids>
        // -> List<unique Order_ids> -> List<Orders>
        context.setMatchOrders(context.getAccountHistories().stream()
                .filter(history -> history.getType().equalsIgnoreCase(MATCH))
                .map(AccountHistory::getDetails).map(Detail::getOrder_id)
                .distinct()
                .map(orderService::getOrder)
                .collect(Collectors.toList()));

        return true;
    }
}
