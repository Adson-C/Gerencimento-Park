package com.ads.park_api.config;

import com.ads.park_api.web.exception.ErrorMessage;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

                ).tags(Arrays.asList(new Tag().name("Usuários").description("Gerenciamento de usuários")))
                .components(new Components().schemas(gerarSchemas()));

        // abri o swagger ui em http://localhost:8088/swagger-ui/index.html
    }
    private Map<String, Schema> gerarSchemas(){

        final Map<String, Schema> schemas = new HashMap<>();


        Map<String, Schema> errorMsgSchema = ModelConverters.getInstance().read(ErrorMessage.class);
        schemas.putAll(errorMsgSchema);

//        schemas.put("UsuarioCreateDto", new Schema().name("UsuarioCreateDto").description("Modelo de dados para criação de um novo usuário"));
//        schemas.put("UsuarioResponseDto", new Schema().name("UsuarioResponseDto").description("Modelo de dados para resposta de usuário"));
//        schemas.put("ErrorMessage", new Schema().name("ErrorMessage").description("Modelo de mensagem de erro para respostas de API"));
        return schemas;
    }
    
}
