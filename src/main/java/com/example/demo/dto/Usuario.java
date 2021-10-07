package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Pattern;
import java.util.List;


@Value
@AllArgsConstructor
@Builder
public class Usuario {
    String name;
    @Pattern(message = "La estructura del correo es incorrecta", regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")
    String email;
    @Pattern(message = "contraseña incorrecta", regexp = "^(?=(?:.*[A-Z]))(?=.*[a-z])(?=(?:.*[0-9]){2}).*")
    String password;
    List<Phone> phones;

}
