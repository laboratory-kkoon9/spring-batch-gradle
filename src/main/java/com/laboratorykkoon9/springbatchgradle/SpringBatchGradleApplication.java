package com.laboratorykkoon9.springbatchgradle;

import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchGradleApplication.class, args);
	}

}
