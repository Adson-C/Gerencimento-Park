package com.ads.park_api.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioCreateDto {

    @Schema(description = "O email do usuário", example = "example@gmail.com", required = true)
    @NotBlank(message = "O nome de usuário é obrigatório")
    @Email(message = "O email deve ser válido", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String username;

    @Schema(description = "Uma senha", example = "1234567", required = true)
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 6, message = "A senha deve ter máximo 6 caracteres")
    private String password;
    
}
