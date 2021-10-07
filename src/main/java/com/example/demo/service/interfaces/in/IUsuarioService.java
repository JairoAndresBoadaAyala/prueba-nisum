package com.example.demo.service.interfaces.in;

import com.example.demo.dto.UpdateResponse;
import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioResponse;
import com.example.demo.dto.UsuarioUpdateRequest;
import com.example.demo.repository.entity.UsuarioEntity;

public interface IUsuarioService {
    UsuarioResponse crearUsuario(Usuario usuario);

    UsuarioEntity consultarUsuario(String id);

    UpdateResponse actualizarUsuario(UsuarioUpdateRequest usuario, String id);
}
