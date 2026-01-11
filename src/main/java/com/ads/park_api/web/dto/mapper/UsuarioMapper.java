package com.ads.park_api.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.ads.park_api.entity.Usuario;
import com.ads.park_api.web.dto.UsuarioCreateDto;
import com.ads.park_api.web.dto.UsuarioResponseDto;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto){
        return new ModelMapper().map(createDto, Usuario.class);
    }

     public static UsuarioResponseDto toDto(Usuario usuario){
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(props);
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }
    // Listar todos os usuários
    public static List<UsuarioResponseDto> toDtoList(List<Usuario> usuarios){
        return usuarios.stream().map(UsuarioMapper::toDto).collect(Collectors.toList());
    }
}
