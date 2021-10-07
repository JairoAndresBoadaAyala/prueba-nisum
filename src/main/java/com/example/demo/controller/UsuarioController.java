package com.example.demo.controller;

import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioResponse;
import com.example.demo.exception.UsuarioException;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping()
    public UsuarioResponse crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{id}")
    public final UsuarioEntity obtenerInformacionPrestamo(@PathVariable("id") String id) {
        return usuarioService.consultarUsuario(id);
    }

    @PutMapping("/{id}")
    public final String actualizarUsuario(@Valid @RequestBody Usuario usuario,
                                          @PathVariable("id") String id) {
        return usuarioService.actualizarUsuario(usuario, id);
    }
}

