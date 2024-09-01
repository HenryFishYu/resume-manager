package com.resume.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ResumeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeManagerApplication.class, args);
	}

}
