package br.com.kaiojoaorobert;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class HackApiEmpresaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackApiEmpresaApplication.class, args);
	}

}
