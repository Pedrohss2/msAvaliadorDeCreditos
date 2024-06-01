package io.github.pedrohss2.msAvaliadorDeCreditos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsavaliadordecreditosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsavaliadordecreditosApplication.class, args);
	}

}
