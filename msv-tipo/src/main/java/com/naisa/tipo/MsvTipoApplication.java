package com.naisa.tipo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.naisa.tipo","com.naisa.commons"})
@EnableFeignClients
public class MsvTipoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvTipoApplication.class, args);
	}
}