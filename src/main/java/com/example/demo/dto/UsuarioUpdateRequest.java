package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Pattern;

@Value
@AllArgsConstructor
@Builder
public class UsuarioUpdateRequest {

    @Pattern(message = "contraseña incorrecta", regexp = "^(?=(?:.*[A-Z]))(?=.*[a-z])(?=(?:.*[0-9]){2}).*")
    String password;
    Integer isactive;

}
