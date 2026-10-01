package com.springboot.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

	// Swagger configuration
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Employee Management System API")
						.version("1.0")
						.description("REST API for managing employee details")
						.contact(new Contact()
								.name("Deepak Limbikai")
								.email("deepak@examp.com")));
		
	}
}
