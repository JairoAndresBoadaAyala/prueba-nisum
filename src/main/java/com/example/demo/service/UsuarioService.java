package com.example.demo.service;

import com.example.demo.dto.Usuario;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.interfaces.out.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity crearUsuario(Usuario usuario){
       return usuarioRepository.crearUsuario(usuario);
    }

    public UsuarioEntity consultarUsuario(int id) {
        return usuarioRepository.consultarUsuario(id);

    }
}
