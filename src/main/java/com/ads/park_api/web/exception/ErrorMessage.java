package com.ads.park_api.web.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;

@Getter  @ToString
@Schema(name = "ErrorMessage", description = "Modelo de mensagem de erro para respostas de API")
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "O caminho da requisição que causou o erro", example = "/api/v1/usuarios")
    private String path;
    @Schema(description = "O método HTTP da requisição que causou o erro", example = "POST")
    private String method;
    @Schema(description = "O código de status HTTP da resposta", example = "400")
    private int status;
    @Schema(description = "O texto do status HTTP da resposta", example = "Bad Request")
    private String statusText;
    @Schema(description = "A mensagem de erro detalhada", example = "Erro de validação")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    public ErrorMessage(HttpServletRequest request, int value, String usuárioOuSenhaInválidos) {}


    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    }
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult bindingResult) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
        addErros(bindingResult);
    }
    private void addErros(BindingResult bindingResult) {
        this.errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            this.errors.put(error.getField(), error.getDefaultMessage());
        }
    }
}
