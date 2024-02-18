package com.example.springsecuritytecsup.controller;

import com.example.springsecuritytecsup.request.UsuarioRequest;
import com.example.springsecuritytecsup.response.Response;
import com.example.springsecuritytecsup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello from spring security";
    }
    @PostMapping("/singUp")
    public Response singUp(@RequestBody UsuarioRequest student)
    {
        return usuarioService.singup(student);
    }
    @PostMapping("/login")
    public Response login(@RequestBody Map<String, String> request)
    {
        return usuarioService.login(request);
    }
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/find/{id}")
    public Response find(@PathVariable Integer id)
    {
        return usuarioService.findById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Integer id)
    {
        return usuarioService.deleteById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/onlyAdmin")
    public String onlyAdmin()
    {
        return "Hello from only admin";
    }
}