package com.pritam.fabrication.ecommerce;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {

//		ConfigurableApplicationContext ctx =
				SpringApplication.run(ECommerceApplication.class, args);

//		Arrays.stream(ctx.getBeanDefinitionNames())
//				.sorted()
//				.forEach(System.out::println);
	}

}
