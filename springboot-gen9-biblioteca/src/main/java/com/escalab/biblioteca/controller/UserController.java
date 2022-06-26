package com.escalab.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.escalab.biblioteca.model.Usuario;
import com.escalab.biblioteca.service.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UserController {

    private final UsuarioService usuarioService;

    @Autowired
    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public @ResponseBody
    List<Usuario> findAll(){
        return usuarioService.listar();
    }
}
