package com.ads.park_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ParkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkApiApplication.class, args);

//          Mostrar uma senha criptografada para teste
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "123456";
        String senhaCriptografada = encoder.encode(senha);
        System.out.println("Senha criptografada: " + senhaCriptografada);


	}

}
