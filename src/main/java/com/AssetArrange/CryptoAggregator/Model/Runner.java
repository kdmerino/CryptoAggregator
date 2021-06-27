package com.AssetArrange.CryptoAggregator.Model;

import com.AssetArrange.CryptoAggregator.Core.TransactionExecuter;
import com.AssetArrange.CryptoAggregator.Core.Transactional;
import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;

public class Runner {
    private static final TransactionExecuter executer = new TransactionExecuter();
    private final ICoinbaseProxy coinbaseProxy;

    public Runner(final ICoinbaseProxy coinbaseProxy) {
        this.coinbaseProxy = coinbaseProxy;
    }

    public Context run(final String choice) {
        // Convert String choice to valid transactional
        return run(Transactional.fromString(choice));
    }

    public Context run(final Transactional transactional) {
        // Set initial Context
        Context context = Context.builder()
                .proxy(coinbaseProxy)
                .build();
        // Execute target program
        executer.execute(transactional, context);
        return context;
    }
}
