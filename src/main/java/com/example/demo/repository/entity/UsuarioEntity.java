package com.example.demo.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "usuario")
public class UsuarioEntity {

    @Id
    String id;

    String name;
    String email;
    String password;
    LocalDateTime created;
    LocalDateTime modified;
    LocalDateTime last_login;
    String token;
    Integer isactive;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<PhoneEntity> phones;

}
