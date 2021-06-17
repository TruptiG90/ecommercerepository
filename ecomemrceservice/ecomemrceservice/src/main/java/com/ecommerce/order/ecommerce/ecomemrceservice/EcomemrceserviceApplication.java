package com.ecommerce.order.ecommerce.ecomemrceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class EcomemrceserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomemrceserviceApplication.class, args);
	}

}
