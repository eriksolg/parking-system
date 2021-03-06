package com.veebzone.parking;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({
		"classpath:parking-system.properties"
})
public class ParkingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ParkingApplication.class);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}