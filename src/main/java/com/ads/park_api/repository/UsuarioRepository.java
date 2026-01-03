package com.ads.park_api.repository;
import com.ads.park_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>
{
    
}
