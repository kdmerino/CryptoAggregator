package com.AssetArrange.CryptoAggregator.init.coinbasePro;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CoinbaseProSettings {
    @Value("${coinbasePro.baseUrl}")
    private String coinbaseProBaseUrl;
    @Value("${coinbasePro.publicKey}")
    private String coinbaseProPublicKey;
    @Value("${coinbasePro.secretKey}")
    private String coinbaseProSecretKey;
    @Value("${coinbasePro.passphrase}")
    private String coinbaseProPassphrase;

}
