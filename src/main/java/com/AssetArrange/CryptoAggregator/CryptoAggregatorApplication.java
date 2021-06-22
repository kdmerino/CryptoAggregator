package com.AssetArrange.CryptoAggregator;

import com.AssetArrange.CryptoAggregator.Core.Signature;
import com.AssetArrange.CryptoAggregator.Core.dto.Account;
import com.AssetArrange.CryptoAggregator.Proxy.CoinbaseProxy;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import com.AssetArrange.CryptoAggregator.Services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class CryptoAggregatorApplication {
	private static final Logger LOG = LoggerFactory.getLogger(CryptoAggregatorApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CryptoAggregatorApplication.class, args);
		final String publicKey, passphrase, baseUrl, secretKey;
		Signature sign;
		ObjectMapper mapper = new ObjectMapper();
		baseUrl = "https://api.pro.coinbase.com/";


		sign = new Signature(secretKey);
		ICoinbaseProxy proxy = new CoinbaseProxy(publicKey, passphrase, baseUrl, sign, mapper);
		AccountService accountService = new AccountService(proxy);
		List<Account> accounts = accountService.getAccounts();
		for (Account account :  accounts) {
			String id = account.getId();
			String currency = account.getCurrency();
			BigDecimal balance = account.getBalance();
			BigDecimal available = account.getAvailable();
			BigDecimal hold = account.getHold();
			String profile_id = account.getProfile_id();
			LOG.info("id={}, currency={}, balance={}, available={}, hold={}, profile_id={}",
					id, currency, balance, available, hold, profile_id);
		}
	}

}
