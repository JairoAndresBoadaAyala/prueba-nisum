package com.example.demo.controller;

import com.example.demo.dto.Usuario;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping()
    public UsuarioEntity crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{id}")
    public final UsuarioEntity obtenerInformacionPrestamo(@PathVariable("id") int id) {
        System.out.println("ingreso al get");
        return usuarioService.consultarUsuario(id);
    }
}

