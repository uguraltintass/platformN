package com.platformN.orderserviceplatformN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EntityScan("com.platformN.orderserviceplatformN.model")
@EnableDiscoveryClient
public class OrderServicePlatformNApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServicePlatformNApplication.class, args);
	}

}
