package com.escalab.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.biblioteca.model.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    //select * from usuario where username = ?
    Usuario findOneByNombre(String username);
}
