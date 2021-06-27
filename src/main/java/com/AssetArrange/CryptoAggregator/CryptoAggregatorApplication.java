package com.AssetArrange.CryptoAggregator;

import com.AssetArrange.CryptoAggregator.Core.Transactional;
import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Model.Runner;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class CryptoAggregatorApplication {
	private static final Logger LOG = LoggerFactory.getLogger(CryptoAggregatorApplication.class);
	private final ICoinbaseProxy coinbaseProxy;

	@Autowired
	public CryptoAggregatorApplication(final ICoinbaseProxy coinbaseProxy) {
		this.coinbaseProxy = coinbaseProxy;
	}

	public static void main(String[] args) {
		SpringApplication.run(CryptoAggregatorApplication.class, args);
	}

	@PostConstruct
	public void init() {
		LOG.info("Launching Runner...");
		Runner runner = new Runner(coinbaseProxy);
		Context context = runner.run(Transactional.READ_ORDERS);
		LOG.info("Terminating Runner...");
	}
}
