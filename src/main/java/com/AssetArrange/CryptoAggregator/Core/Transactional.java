package com.AssetArrange.CryptoAggregator.Core;

import java.util.Arrays;

public enum Transactional {
    PREPARE_ASSETS,
    EXPERIMENTAL;

    public static Transactional fromString(final String choice) {
        return Arrays.stream(values())
                .filter(transact -> transact.name().equalsIgnoreCase(choice))
                .findAny().orElseThrow();
    }
}
