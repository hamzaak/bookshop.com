package com.bookshop.localizationms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.bookshop.localizationms" })
public class LocalizationMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalizationMsApplication.class, args);
	}

}
