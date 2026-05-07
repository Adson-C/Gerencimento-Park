package com.ads.park_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_CLIENTE;


    private LocalDateTime dataCriacao;

    private LocalDateTime dataMotificacao;

    private String criadoPor;

    private String modificadoPor;

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // ToString
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", dataCriacao=" + dataCriacao +
                ", dataMotificacao=" + dataMotificacao +
                ", criadoPor='" + criadoPor + '\'' +
                ", modificadoPor='" + modificadoPor + '\'' +
                '}';
    }

    public enum Role {
        ROLE_ADMIN,
        ROLE_CLIENTE
    }
}
