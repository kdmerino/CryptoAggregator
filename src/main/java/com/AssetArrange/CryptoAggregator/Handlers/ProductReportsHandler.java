package com.AssetArrange.CryptoAggregator.Handlers;

import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Core.context.Output;
import com.AssetArrange.CryptoAggregator.Core.dto.Order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductReportsHandler implements IHandler {
    private static final String BUY = "buy";
    @Override
    public boolean execute(IContext context) {
        Output output = (Output) context;
        Map<String, List<Order>> map = (Map<String, List<Order>>) output.getReturnValue();
        output.setReturnValue(map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream()
                        .map(order -> Float.parseFloat(order.getExecuted_value()) *
                             (order.getSide().equalsIgnoreCase(BUY) ? -1: 1))
                        .reduce(Float::sum))));
        return true;
    }
}
