package com.ads.park_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                    new Info()
                    .title("REST API - Spring Park")
                    .description("API para gestão de vagas de estacionamento")
                    .version("v1.0.0")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                    .contact(new Contact().name("Adson Sá").email("adsonconcecao@yahoo.com.br"))
                );
    }
    
}
