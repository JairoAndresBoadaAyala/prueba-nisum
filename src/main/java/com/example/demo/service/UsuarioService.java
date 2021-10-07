package com.example.demo.service;

import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioResponse;
import com.example.demo.exception.UsuarioException;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.interfaces.out.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse crearUsuario(Usuario usuario) {
        var validarCorreo = usuarioRepository.consultarPorCorreo(usuario.getEmail());
        if (!validarCorreo.isEmpty()) {
            throw new UsuarioException("El correo ya registrado");
        } else {
            var usuarioRespuesta = usuarioRepository.crearUsuario(usuario);
            return UsuarioResponse.builder()
                    .id(usuarioRespuesta.getId())
                    .created(usuarioRespuesta.getCreated())
                    .modified(usuarioRespuesta.getModified())
                    .last_login(usuarioRespuesta.getLast_login())
                    .token(usuarioRespuesta.getToken())
                    .isactive(usuarioRespuesta.getIsactive())
                    .build();
        }
    }

    public UsuarioEntity consultarUsuario(String id) {
        return usuarioRepository.consultarUsuario(id);
    }

    public String actualizarUsuario(Usuario usuario, String id) {
        usuarioRepository.actualizarUsuario(usuario, id);
        return "Se actualizo el usuario de manera correcta";
    }
}
