package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class Usuario {
    String name;
    String email;
    String password;
    List<Phone> phones;

}
