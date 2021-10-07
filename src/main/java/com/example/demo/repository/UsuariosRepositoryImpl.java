package com.example.demo.repository;

import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioUpdateRequest;
import com.example.demo.repository.entity.PhoneEntity;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.interfaces.out.UsuarioRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Repository
public class UsuariosRepositoryImpl implements UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UsuarioEntity crearUsuario(Usuario usuario) {
        var phones = usuario.getPhones().stream().map(p -> PhoneEntity.builder().citycode(p.getCityCode()).countrycode(p.getCountryCode()).number(p.getNumber()).build()).collect(Collectors.toList());
        var dateNow = LocalDateTime.now();

        var usuarioEntity = UsuarioEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(usuario.getName())
                .email(usuario.getEmail())
                .created(dateNow)
                .modified(dateNow)
                .last_login(dateNow)
                .token(UUID.randomUUID().toString())
                .isactive(1)
                .password(usuario.getPassword())
                .phones(phones)
                .build();

        em.persist(usuarioEntity);
        return usuarioEntity;
    }

    @Override
    public UsuarioEntity consultarUsuario(String id) {
        return em.find(UsuarioEntity.class, id);
    }

    @Override
    public String consultarPorCorreo(String email) {
        Query nativeQuery = em.createNativeQuery("SELECT a.email FROM usuario a where a.email = :pEmail")
                .setParameter("pEmail", email);
        return (String) nativeQuery.getResultList().stream().findFirst().orElse("");
    }

    @Override
    @Transactional
    public int actualizarUsuario(UsuarioUpdateRequest usuario, String id) {
        return em.createNativeQuery("UPDATE usuario SET password = :vPassWord, modified= :vModified, isactive= :vIsactive WHERE id = :vId")
                .setParameter("vPassWord", usuario.getPassword())
                .setParameter("vModified", LocalDateTime.now())
                .setParameter("vIsactive", usuario.getIsactive())
                .setParameter("vId", id)
                .executeUpdate();
    }

}
