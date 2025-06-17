package com.jeffrey.paymentconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jeffrey")
public class PaymentConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentConsumerApplication.class, args);
	}

}
