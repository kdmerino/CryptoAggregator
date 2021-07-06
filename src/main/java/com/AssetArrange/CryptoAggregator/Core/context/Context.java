package com.AssetArrange.CryptoAggregator.Core.context;

import com.AssetArrange.CryptoAggregator.Model.Asset;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Context implements IContext {
    private ICoinbaseProxy proxy;
    private Map<String, Asset> assetMap;

    // Chained output, transient.
    private Object output;
}
