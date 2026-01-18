package com.ads.park_api.web.dto;


import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioSenhaDto {

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 6, message = "A senha deve ter máximo 6 caracteres")
    private String senhaAtual;
    
    @NotBlank(message = "A nova senha é obrigatória")
    @Size(min = 6, max = 6, message = "A nova senha deve ter máximo 6 caracteres")
    private String novaSenha;
    @NotBlank(message = "A confirmação de senha é obrigatória")
    @Size(min = 6, max = 6, message = "A confirmação de senha deve ter máximo 6 caracteres")
    private String confirmaSenha;

   
}
