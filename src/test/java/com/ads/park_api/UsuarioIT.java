package com.ads.park_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.ads.park_api.web.dto.UsuarioCreateDto;
import com.ads.park_api.web.dto.UsuarioResponseDto;
import com.ads.park_api.web.exception.ErrorMessage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 @Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
 @Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
 @ActiveProfiles("h2")
public class UsuarioIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createUsuario_ComUsernameEPasswordValidos_RetornarUsuarioCriadoComStatus201() {
       UsuarioResponseDto responseDto = testClient
               .post()
               .uri("/api/v1/usuarios")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioCreateDto("tody@gmail.com", "123456"))
               .exchange()
               .expectStatus().isCreated()
               .expectBody(UsuarioResponseDto.class)
               .returnResult()
               .getResponseBody();
        org.assertj.core.api.Assertions.assertThat(responseDto).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseDto.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseDto.getUsername()).isEqualTo("tody@gmail.com");
        org.assertj.core.api.Assertions.assertThat(responseDto.getRole()).isEqualTo("CLIENTE");

     }
     @Test
    public void createUsuario_ComUsernameInvalidos_RetornarErrorMessageStatus422() {
       ErrorMessage responseDto = testClient
               .post()
               .uri("/api/v1/usuarios")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioCreateDto("", "123456"))
               .exchange()
               .expectStatus().isEqualTo(422)
               .expectBody(ErrorMessage.class)
               .returnResult()
               .getResponseBody();
        org.assertj.core.api.Assertions.assertThat(responseDto).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseDto.getStatus()).isEqualTo(422);

        responseDto = testClient
               .post()
               .uri("/api/v1/usuarios")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioCreateDto("adson@", "123456"))
               .exchange()
               .expectStatus().isEqualTo(422)
               .expectBody(ErrorMessage.class)
               .returnResult()
               .getResponseBody();
        org.assertj.core.api.Assertions.assertThat(responseDto).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseDto.getStatus()).isEqualTo(422);

        responseDto = testClient
               .post()
               .uri("/api/v1/usuarios")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioCreateDto("adson@gmail", "123456"))
               .exchange()
               .expectStatus().isEqualTo(422)
               .expectBody(ErrorMessage.class)
               .returnResult()
               .getResponseBody();
        org.assertj.core.api.Assertions.assertThat(responseDto).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseDto.getStatus()).isEqualTo(422);
     }

      @Test
    public void createUsuario_ComPasswordInvalidos_RetornarErrorMessageStatus422() {
       ErrorMessage responseDto = testClient
               .post()
               .uri("/api/v1/usuarios")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioCreateDto("adson@gmail.com.br", ""))
               .exchange()
               .expectStatus().isEqualTo(422)
               .expectBody(ErrorMessage.class)
               .returnResult()
               .getResponseBody();
        org.assertj.core.api.Assertions.assertThat(responseDto).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseDto.getStatus()).isEqualTo(422);

        responseDto = testClient
               .post()
               .uri("/api/v1/usuarios")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioCreateDto("adson@gmail.com.br", "123"))
               .exchange()
               .expectStatus().isEqualTo(422)
               .expectBody(ErrorMessage.class)
               .returnResult()
               .getResponseBody();
        org.assertj.core.api.Assertions.assertThat(responseDto).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseDto.getStatus()).isEqualTo(422);

        responseDto = testClient
               .post()
               .uri("/api/v1/usuarios")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioCreateDto("adson@gmail.com.br", "123456785"))
               .exchange()
               .expectStatus().isEqualTo(422)
               .expectBody(ErrorMessage.class)
               .returnResult()
               .getResponseBody();
        org.assertj.core.api.Assertions.assertThat(responseDto).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseDto.getStatus()).isEqualTo(422);
     }

    @Test
    public void createUsuario_ComUsernameRepetido_RetornarErrorMessageStatus409() {
       ErrorMessage responseDto = testClient
               .post()
               .uri("/api/v1/usuarios")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioCreateDto("admin@gmail.com.br", "123456"))
               .exchange()
               .expectStatus().isEqualTo(409)
               .expectBody(ErrorMessage.class)
               .returnResult()
               .getResponseBody();
        org.assertj.core.api.Assertions.assertThat(responseDto).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseDto.getStatus()).isEqualTo(409);

     }
    
}
