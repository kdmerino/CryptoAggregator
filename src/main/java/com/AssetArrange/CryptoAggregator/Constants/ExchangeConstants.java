package com.AssetArrange.CryptoAggregator.Constants;

import javax.crypto.Mac;
import java.security.NoSuchAlgorithmException;

public class ExchangeConstants {

    public static Mac SHARED_MAC;

    static {
        try {
            SHARED_MAC = Mac.getInstance(Constants.HMAC_SHA256);
        } catch (NoSuchAlgorithmException nsaEx) {
            nsaEx.printStackTrace();
        }
    }
}
