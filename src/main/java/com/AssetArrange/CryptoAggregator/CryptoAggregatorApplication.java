package com.AssetArrange.CryptoAggregator;

import com.AssetArrange.CryptoAggregator.Core.Transactional;
import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Core.context.Output;
import com.AssetArrange.CryptoAggregator.Model.Runner;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


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
		Long startTime = System.nanoTime(), endTime;
		Context context = runner.run(Transactional.READ_ORDERS);
		endTime = System.nanoTime();
		LOG.info("Order reading took: {}", TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS));

		startTime = endTime;
		Output output = (Output) runner.chainRun(Transactional.EXPERIMENTAL, context.getMatchOrders());
		endTime = System.nanoTime();
		LOG.info("Experimental chain took: {}", TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS));
		Map<String, Double> report = (Map<String, Double>) output.getReturnValue();
		LOG.info("net effect: {}", report.values().stream().reduce(Double::sum));
		LOG.info("Terminating Runner...");
	}
}
