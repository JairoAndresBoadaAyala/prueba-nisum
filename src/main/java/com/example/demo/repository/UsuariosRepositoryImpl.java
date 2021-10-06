package com.example.demo.repository;

import com.example.demo.dto.Usuario;
import com.example.demo.repository.entity.PhoneEntity;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.interfaces.out.UsuarioRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.stream.Collectors;


@Repository
public class UsuariosRepositoryImpl implements UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UsuarioEntity crearUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(null,usuario.getName(), usuario.getEmail(), usuario.getPassword(),usuario.getPhones().stream().map(p-> PhoneEntity.builder().citycode(p.getCityCode()).countrycode(p.getCountryCode()).number(p.getNumber()).build()).collect(Collectors.toList()));
        em.persist(usuarioEntity);
        return usuarioEntity;
    }

    @Override
    public UsuarioEntity consultarUsuario(int id) {
        return em.find(UsuarioEntity.class,id);
    }

}
