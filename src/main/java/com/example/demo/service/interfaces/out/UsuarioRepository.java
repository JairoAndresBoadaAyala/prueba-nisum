package com.example.demo.service.interfaces.out;

import com.example.demo.dto.Usuario;
import com.example.demo.repository.entity.UsuarioEntity;

public interface UsuarioRepository {

    UsuarioEntity crearUsuario(Usuario usuario);

    UsuarioEntity consultarUsuario(int id);
}
