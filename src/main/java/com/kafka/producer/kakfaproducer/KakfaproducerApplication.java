package com.kafka.producer.kakfaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KakfaproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakfaproducerApplication.class, args);
	}

}
