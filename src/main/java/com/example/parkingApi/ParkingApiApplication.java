package com.example.parkingApi;

import com.example.parkingApi.model.ParkingDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = ParkingDao.class)
public class ParkingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApiApplication.class, args);
	}

	@Bean
	ServletWebServerFactory servletWebServerFactory(){
		return new TomcatServletWebServerFactory();
	}
}
