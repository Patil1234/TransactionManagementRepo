package com.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration.class)
public class TransactionsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsManagementApplication.class, args);
	}

}
