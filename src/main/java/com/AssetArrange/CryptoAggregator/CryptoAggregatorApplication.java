package com.AssetArrange.CryptoAggregator;

import com.AssetArrange.CryptoAggregator.Constants.Constants;
import com.AssetArrange.CryptoAggregator.Core.Detail;
import com.AssetArrange.CryptoAggregator.Core.Signature;
import com.AssetArrange.CryptoAggregator.Core.dto.Account;
import com.AssetArrange.CryptoAggregator.Core.dto.AccountHistory;
import com.AssetArrange.CryptoAggregator.Core.dto.Order;
import com.AssetArrange.CryptoAggregator.Proxy.CoinbaseProxy;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import com.AssetArrange.CryptoAggregator.Services.AccountService;
import com.AssetArrange.CryptoAggregator.Services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
public class CryptoAggregatorApplication {
	private static final Logger LOG = LoggerFactory.getLogger(CryptoAggregatorApplication.class);
	private static final Gson GSON = new Gson();

	public static void main(String[] args) {
		SpringApplication.run(CryptoAggregatorApplication.class, args);
		final String publicKey, passphrase, baseUrl, secretKey;
		Signature sign;
		ObjectMapper mapper = new ObjectMapper();
		baseUrl = "https://api.pro.coinbase.com/";

		/*
		  DO NOT UPLOAD ---- BELOW
		 */

		/*
		  DO NOT UPLOAD ---- ABOVE
		 */

		sign = new Signature(secretKey);
		ICoinbaseProxy proxy = new CoinbaseProxy(publicKey, passphrase, baseUrl, sign, mapper);
		AccountService accountService = new AccountService(proxy);
		OrderService orderService = new OrderService(proxy);
		List<Account> accounts = accountService.getAccounts();

		long starTime = System.nanoTime();
		List<List<Order>> orders = accounts.stream()
			.map(Account::getId).map(accountService::getAccountHistory).map(histories -> histories.stream()
				.filter(history -> history.getType().equalsIgnoreCase(Constants.MATCH))
				.map(AccountHistory::getDetails).map(Detail::getOrder_id)
				.collect(Collectors.toSet())
				.stream()
				.map(orderService::getOrder)
				.collect(Collectors.toList()))
			.collect(Collectors.toList());
		LOG.info("stream process took: {} seconds",
				TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - starTime));
		orders.forEach(orderList -> orderList.forEach(order -> LOG.info("order: {}", order.toString())));

	}

}
