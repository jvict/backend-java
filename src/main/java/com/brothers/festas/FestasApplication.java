package com.brothers.festas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching

public class FestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FestasApplication.class, args);
	}
}
