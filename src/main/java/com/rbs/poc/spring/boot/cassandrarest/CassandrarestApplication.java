package com.rbs.poc.spring.boot.cassandrarest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableAsync
public class CassandrarestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandrarestApplication.class, args);
	}
}
