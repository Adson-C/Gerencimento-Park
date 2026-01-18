package com.ads.park_api.web.contoller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.park_api.entity.Usuario;
import com.ads.park_api.service.UsuarioService;
import com.ads.park_api.web.dto.UsuarioCreateDto;
import com.ads.park_api.web.dto.UsuarioResponseDto;
import com.ads.park_api.web.dto.UsuarioSenhaDto;
import com.ads.park_api.web.dto.mapper.UsuarioMapper;
import com.ads.park_api.web.exception.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Cria um novo usuário", description = "Recurso para criar um novo usuário",
        responses = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = UsuarioResponseDto.class))),

            @ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado", content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorMessage.class))),
            
                @ApiResponse(responseCode = "422", description = "Recurso não encontrado por dados de entrada errados",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorMessage.class)))
            })

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create( @Valid @RequestBody UsuarioCreateDto usuarioCreateDto){
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(usuarioCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    

    @Operation(summary = "Busca um usuário pelo ID", description = "Retorna os dados de um usuário específico pelo ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorMessage.class)))
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id){
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    // Listar todos os usuários
    @Operation(
        summary = "Lista todos os usuários",
        description = "Retorna uma lista de todos os usuários cadastrados",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso", 
                content = @Content(mediaType = "application/json", 
                                   schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", 
                content = @Content(mediaType = "application/json", 
                                   schema = @Schema(implementation = ErrorMessage.class)))
        }
    )
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAllUsers() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toDtoList(users));
    }

    // PatchMapping to update user password
    @Operation(
        summary = "Atualiza a senha do usuário",
        description = "Permite alterar a senha de um usuário informado o ID",
        responses = {
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
                content = @Content(mediaType = "application/json", 
                                   schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos", 
                content = @Content(mediaType = "application/json", 
                                   schema = @Schema(implementation = ErrorMessage.class)))
        }
    )
    @SuppressWarnings("unused")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
       Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

}