package com.AssetArrange.CryptoAggregator.Handlers;

import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Model.Asset;
import com.AssetArrange.CryptoAggregator.Services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

public class ProductReportsHandler implements IHandler {
    private static final String BUY = "buy";
    private static final String TO_USD = "-USD";
    private static final Logger LOG = LoggerFactory.getLogger(ProductReportsHandler.class);

    @Override
    public boolean execute(IContext context) {
        Context output = (Context) context;
        ProductService productService = new ProductService(output.getProxy());
        Map<String, Asset> assetMap = output.getAssetMap();
        assetMap.forEach((key, value) -> {
                    value.setBalance(BigDecimal.valueOf(
                            value.getOrders().stream()
                                    .mapToDouble(order -> Double.parseDouble(order.getExecuted_value()) *
                                            (order.getSide().equalsIgnoreCase(BUY) ? -1.0 : 1.0))
                                    .reduce(Double::sum)
                                    .orElse(0.0))
                    );
                    try {
                        value.setPrice(Double.parseDouble(
                                productService.getTickers(key + TO_USD).getPrice()));
                        value.setPending(value.getVolume().multiply(value.getPrice()));
                    } catch (Exception e) {
                        LOG.info("ProductReportsHandler failed to fetch price of account = {}, error msg = {}",
                                key, e.getMessage());
                        value.setPrice(BigDecimal.ZERO);
                        value.setPending(BigDecimal.ZERO);
                    }
                });

        return true;
    }
}
