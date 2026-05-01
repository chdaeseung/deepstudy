package com.deepstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DeepstudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepstudyApplication.class, args);
	}

}
