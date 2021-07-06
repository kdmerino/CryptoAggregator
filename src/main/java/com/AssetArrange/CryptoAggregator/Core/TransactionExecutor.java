package com.AssetArrange.CryptoAggregator.Core;

import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Handlers.AccountsHandler;
import com.AssetArrange.CryptoAggregator.Handlers.AccountsOrderHandler;
import com.AssetArrange.CryptoAggregator.Handlers.ProductReportsHandler;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class TransactionExecutor {
    private final Map<Transactional, Transaction> transactionMap;
    private final ICoinbaseProxy proxy;

    @Autowired
    public TransactionExecutor(final ICoinbaseProxy proxy) {
        this.proxy = proxy;
        transactionMap = new HashMap<>();
        transactionMap.put(Transactional.PREPARE_ASSETS, new Transaction()
                .register(new AccountsHandler())
                .register(new AccountsOrderHandler())
                .register(new ProductReportsHandler()));
        transactionMap.put(Transactional.EXPERIMENTAL, new Transaction());
    }

    public IContext authExecute(final Transactional transactional) {
        Context context = Context.builder()
                .proxy(this.proxy)
                .build();

        doExecute(transactional, context);
        return context;
    }

    public IContext doExecute(final Transactional transactional, final IContext iContext) {
        transactionMap.get(transactional).execute(iContext);
        return iContext;
    }
}
