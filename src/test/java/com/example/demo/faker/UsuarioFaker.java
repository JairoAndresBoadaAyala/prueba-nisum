package com.example.demo.faker;

import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioUpdateRequest;
import com.example.demo.repository.entity.UsuarioEntity;

public class UsuarioFaker {

    public static Usuario buildUsuario() {
        return Usuario.builder().email("pepito@mgia.com").name("pepito").password("ppps12A").build();
    }

    public static UsuarioEntity buildUsuarioEntity() {
        return UsuarioEntity.builder().email("pepito@mgia.com").name("pepito").password("ppps12A").isactive(1).build();
    }

    public static UsuarioUpdateRequest buildUsuarioUpdateRequest() {
        return UsuarioUpdateRequest.builder().password("ppps12A").isactive(0).build();
    }

}
