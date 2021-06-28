package com.AssetArrange.CryptoAggregator.Core;

import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Handlers.AccountsHandler;
import com.AssetArrange.CryptoAggregator.Handlers.AccountsHistoryHandler;
import com.AssetArrange.CryptoAggregator.Handlers.MatchOrderHandler;
import com.AssetArrange.CryptoAggregator.Handlers.ProductOrdersHandler;
import com.AssetArrange.CryptoAggregator.Handlers.ProductReportsHandler;

import java.util.HashMap;
import java.util.Map;

public class TransactionExecuter {
    private final Map<Transactional, Transaction> transactionMap;

    public TransactionExecuter() {
        transactionMap = new HashMap<>();
        transactionMap.put(Transactional.READ_ORDERS, new Transaction()
                .register(new AccountsHandler())
                .register(new AccountsHistoryHandler())
                .register(new MatchOrderHandler()));
        transactionMap.put(Transactional.EXPERIMENTAL, new Transaction()
                .register(new ProductOrdersHandler())
                .register(new ProductReportsHandler()));
    }

    public void execute(final Transactional transactional, final IContext iContext) {
        transactionMap.get(transactional).execute(iContext);
    }
}
