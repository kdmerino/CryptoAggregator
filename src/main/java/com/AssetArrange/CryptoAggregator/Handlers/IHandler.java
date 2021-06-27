package com.AssetArrange.CryptoAggregator.Handlers;

import com.AssetArrange.CryptoAggregator.Core.context.IContext;

public interface IHandler {
    boolean execute(IContext context);
}
