package com.AssetArrange.CryptoAggregator.Core;

import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Handlers.IHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private final List<IHandler> handlers = new ArrayList<>();
    private final Logger LOG = LoggerFactory.getLogger(Transaction.class);

    public Transaction register(IHandler handler) {
        this.handlers.add(handler);
        return this;
    }

    public void execute(IContext context) {
        try {
            handlers.forEach(handler -> handler.execute(context));
        } catch (Exception e) {
            LOG.error("Error executing handler, because: {}", e.getMessage(), e);
        }
    }

}
