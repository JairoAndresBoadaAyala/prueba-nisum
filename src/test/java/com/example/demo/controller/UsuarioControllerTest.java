package com.example.demo.controller;

import com.example.demo.service.UsuarioService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    private static final String URL="/usuario";

    @MockBean
    private  UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearUsuarioCorreIncorrecto() throws Exception {
        this.mockMvc.perform(post(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(buildUsuario("pepito","jjjj.co","aB12agas")))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje")
                        .value("La estructura del correo es incorrecta"));
    }

    @Test
    void crearUsuarioPasswordIncorrecto() throws Exception {
        this.mockMvc.perform(post(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(buildUsuario("pepito","pepito@gmai.co","oooos5546")))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje")
                        .value("contraseña incorrecta"));
    }

    @Test
    void crearUsuario() throws Exception {
        this.mockMvc.perform(post(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(buildUsuario("pepito","pepito@gmai.co","A2sa1sg")))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void obtenerInformacionUsuario() throws Exception {
        this.mockMvc.perform(get(URL+"/{id}",1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void actualizarUsuarioPasswordIncorrecto() throws Exception {
        this.mockMvc.perform(put(URL+"/{id}",1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(buildUpdateUsuario("passsA",0)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje")
                        .value("contraseña incorrecta"));
    }

    @Test
    void actualizarUsuario() throws Exception {
        this.mockMvc.perform(put(URL+"/{id}",1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(buildUpdateUsuario("passsA23",0)))
                .andExpect(status().isOk());
    }



    private String buildUsuario(String name, String email, String password) {
        Map<String, Object> map = Map.of(
                "name", name,
                "email", email,
                "password",password
        );
        return new JSONObject(map).toString();
    }

    private String buildUpdateUsuario(String password, int isActive) {
        Map<String, Object> map = Map.of(
                "password", password,
                "isactive", isActive
        );
        return new JSONObject(map).toString();
    }
}
