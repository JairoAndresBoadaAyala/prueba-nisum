package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioResponse {

    String id;
    LocalDateTime created;
    LocalDateTime modified;
    LocalDateTime last_login;
    String token;
    Integer isactive;

}
