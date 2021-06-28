package com.AssetArrange.CryptoAggregator.Core.context;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Output implements IContext {
    private Object returnValue;
}
