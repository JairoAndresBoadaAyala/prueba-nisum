package com.example.demo.service.interfaces.out;

import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioUpdateRequest;
import com.example.demo.repository.entity.UsuarioEntity;

public interface UsuarioRepository {

    UsuarioEntity crearUsuario(Usuario usuario);

    UsuarioEntity consultarUsuario(String id);

    String consultarPorCorreo(String email);

    int actualizarUsuario(UsuarioUpdateRequest usuario, String id);
}
