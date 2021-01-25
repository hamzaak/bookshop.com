package com.bookshop.shoppingcartms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages={"com.bookshop.shoppingcartms"})
@EnableFeignClients
public class ShoppingcartMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartMsApplication.class, args);
	}

}
