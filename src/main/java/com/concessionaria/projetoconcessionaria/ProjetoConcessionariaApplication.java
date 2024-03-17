package com.concessionaria.projetoconcessionaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjetoConcessionariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoConcessionariaApplication.class, args);
	}

}
