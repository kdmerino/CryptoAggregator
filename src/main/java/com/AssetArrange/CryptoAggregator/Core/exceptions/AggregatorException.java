package com.AssetArrange.CryptoAggregator.Core.exceptions;

public class AggregatorException extends RuntimeException {
    private final String msg;

    public AggregatorException(final String errorMsg) {
        msg = errorMsg;
    }
}
