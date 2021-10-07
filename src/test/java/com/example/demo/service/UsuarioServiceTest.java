package com.example.demo.service;

import com.example.demo.exception.UsuarioException;
import com.example.demo.faker.UsuarioFaker;
import com.example.demo.service.interfaces.out.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    private static final String USUARIO_ID = "1245";
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;


    @Test
    void crearUsuarioConCorreoExistenteError() {
        var usuario = UsuarioFaker.buildUsuario();
        when(usuarioRepository.consultarPorCorreo(usuario.getEmail())).thenReturn(usuario.getEmail());
        assertThatThrownBy(() -> usuarioService.crearUsuario(usuario))
                .isInstanceOf(UsuarioException.class);
        verify(usuarioRepository, times(1)).consultarPorCorreo(anyString());
    }

    @Test
    void crearUsuario() {
        var usuario = UsuarioFaker.buildUsuario();
        when(usuarioRepository.consultarPorCorreo(usuario.getEmail())).thenReturn("");
        when(usuarioRepository.crearUsuario(usuario)).thenReturn(UsuarioFaker.buildUsuarioEntity());
        var usuarioResponse = usuarioService.crearUsuario(usuario);
        assertThat(usuarioResponse).isNotNull();
        assertThat(usuarioResponse.getIsactive()).isEqualTo(1);
        verify(usuarioRepository, times(1)).consultarPorCorreo(anyString());
    }

    @Test
    void consultarUsuario() {
        when(usuarioRepository.consultarUsuario(USUARIO_ID)).thenReturn(UsuarioFaker.buildUsuarioEntity());
        var usuario = usuarioService.consultarUsuario(USUARIO_ID);
        verify(usuarioRepository, times(1)).consultarUsuario(anyString());
    }

    @Test
    void actualizarUsuarioConUsuarioNoExistenteError() {
        var usuario = UsuarioFaker.buildUsuarioUpdateRequest();
        when(usuarioRepository.actualizarUsuario(usuario, USUARIO_ID)).thenReturn(0);
        assertThatThrownBy(() -> usuarioService.actualizarUsuario(usuario, USUARIO_ID))
                .isInstanceOf(UsuarioException.class);
        verify(usuarioRepository, times(1)).actualizarUsuario(usuario, USUARIO_ID);
    }

    @Test
    void actualizarUsuario() {
        var usuario = UsuarioFaker.buildUsuarioUpdateRequest();
        when(usuarioRepository.actualizarUsuario(usuario, USUARIO_ID)).thenReturn(1);
        var response = usuarioService.actualizarUsuario(usuario, USUARIO_ID);
        assertThat(response).isNotNull();
        assertThat(response.getMensaje()).isEqualTo("Se actualizo el usuario de manera correcta");
        verify(usuarioRepository, times(1)).actualizarUsuario(usuario, USUARIO_ID);
    }
}
