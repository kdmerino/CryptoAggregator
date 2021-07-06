package com.AssetArrange.CryptoAggregator;

import com.AssetArrange.CryptoAggregator.Core.TransactionExecutor;
import com.AssetArrange.CryptoAggregator.Core.Transactional;
import com.AssetArrange.CryptoAggregator.Core.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class CryptoAggregatorApplication {
	private static final Logger LOG = LoggerFactory.getLogger(CryptoAggregatorApplication.class);
	private final TransactionExecutor executor;

	@Autowired
	public CryptoAggregatorApplication(final TransactionExecutor executor) {
		this.executor = executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(CryptoAggregatorApplication.class, args);
	}

	@PostConstruct
	public void init() {
		/*
		 * TODO:
		 *
		 */
		LOG.info("Launching Runner...");
		Long startTime = System.nanoTime(), endTime;
		executor.authExecute(Transactional.PREPARE_ASSETS);
		endTime = System.nanoTime();
		LOG.info("Order reading took: {}", TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS));

		LOG.info("Terminating Runner...");
	}
}
