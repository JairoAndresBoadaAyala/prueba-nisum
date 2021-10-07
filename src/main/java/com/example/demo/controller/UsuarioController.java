package com.example.demo.controller;

import com.example.demo.dto.UpdateResponse;
import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioResponse;
import com.example.demo.dto.UsuarioUpdateRequest;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.interfaces.in.IUsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping()
    public UsuarioResponse crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{id}")
    public final UsuarioEntity obtenerInformacionUsuario(@PathVariable("id") String id) {
        return usuarioService.consultarUsuario(id);
    }

    @PutMapping("/{id}")
    public final UpdateResponse actualizarUsuario(@Valid @RequestBody UsuarioUpdateRequest usuario,
                                                  @PathVariable("id") String id) {
        return usuarioService.actualizarUsuario(usuario, id);
    }
}

