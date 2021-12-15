package com.gbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GBSUserMicroserviceApp {

	public static void main(String[] args) {
		System.out.println("SPRING BOOT APPLICATION (WEB APP)");
		SpringApplication.run(GBSUserMicroserviceApp.class, args);
	}

}
