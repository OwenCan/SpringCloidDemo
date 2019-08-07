package com.sprringcloud.microweathereurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaServer
@RestController
public class MicroWeatherEurekaServerApplication {

	@RequestMapping("/hello")
	public String home() {
		return "Welcome home";
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroWeatherEurekaServerApplication.class, args);
	}

}
