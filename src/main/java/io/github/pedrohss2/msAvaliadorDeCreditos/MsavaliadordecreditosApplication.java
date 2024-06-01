package io.github.pedrohss2.msAvaliadorDeCreditos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MsavaliadordecreditosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsavaliadordecreditosApplication.class, args);
	}

}
