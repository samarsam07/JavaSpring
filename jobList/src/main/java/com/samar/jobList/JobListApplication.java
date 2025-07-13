package com.samar.jobList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class JobListApplication {


	public static void main(String[] args) {
		SpringApplication.run(JobListApplication.class, args);
	}

}
