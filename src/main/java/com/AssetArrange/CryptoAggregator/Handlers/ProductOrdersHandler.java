package com.AssetArrange.CryptoAggregator.Handlers;

import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Core.context.Output;
import com.AssetArrange.CryptoAggregator.Core.dto.Order;

import java.util.List;
import java.util.stream.Collectors;

public class ProductOrdersHandler implements IHandler {
    @Override
    public boolean execute(IContext context) {
        Output output = (Output) context;
        List<Order> orders = (List<Order>) output.getReturnValue();
        output.setReturnValue(orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct_id)));
        return true;
    }
}
