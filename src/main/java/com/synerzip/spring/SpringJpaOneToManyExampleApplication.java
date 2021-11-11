package com.synerzip.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringJpaOneToManyExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaOneToManyExampleApplication.class, args);
	}

}
