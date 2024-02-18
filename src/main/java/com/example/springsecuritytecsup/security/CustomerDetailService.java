package com.example.springsecuritytecsup.security;

import com.example.springsecuritytecsup.model.Usuario;
import com.example.springsecuritytecsup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerDetailService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> studentOptional= usuarioRepository.findByEmail(username);
        if (studentOptional.isPresent())
        {
            usuario = studentOptional.get();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(usuario.getRole().getName())));
            return new User(usuario.getEmail(), usuario.getPassword(),authorities);
        }
        else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
    public Usuario getStudent()
    {
        return usuario;
    }
}