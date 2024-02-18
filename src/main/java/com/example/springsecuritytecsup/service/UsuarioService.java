package com.example.springsecuritytecsup.service;

import com.example.springsecuritytecsup.model.Role;
import com.example.springsecuritytecsup.model.Usuario;
import com.example.springsecuritytecsup.repository.RoleRepository;
import com.example.springsecuritytecsup.repository.UsuarioRepository;
import com.example.springsecuritytecsup.request.UsuarioRequest;
import com.example.springsecuritytecsup.response.Response;
import com.example.springsecuritytecsup.security.CustomerDetailService;
import com.example.springsecuritytecsup.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomerDetailService customerDetailService;
    public Response singup(UsuarioRequest usuarioRequest)
    {
        Optional<Usuario> studentOptional = usuarioRepository.findByEmail(usuarioRequest.getEmail());
        if (studentOptional.isPresent())
        {
            throw new RuntimeException("El email ya esta registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setName(usuarioRequest.getName());
        usuario.setLastname(usuarioRequest.getLastname());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setPassword(usuarioRequest.getPassword());
        Optional<Role> roleOptional = roleRepository.findByName(usuarioRequest.getRole());
        if(roleOptional.isPresent())
        {
            usuario.setRole(roleOptional.get());
            Usuario usuarioSaved = usuarioRepository.save(usuario);
            return new Response("Usuario registrado con exito", "200", Optional.of(usuarioSaved));
        }
        else {
            throw new RuntimeException("Rol no encontrado");
        }
    }
    public Response login(Map<String, String> request)
    {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.get("email"), request.get("password"))
            );
            if (authentication.isAuthenticated())
            {
                String token = jwtUtil.generateToken(customerDetailService.getStudent().getEmail(), customerDetailService.getStudent().getRole().getName().toString());
                return new Response("Autenticado", "200", Optional.of(token));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new Response("No autenticado", "401", Optional.empty());
    }
    public Response findById(Integer id)
    {
        Optional<Usuario> studentOptional = usuarioRepository.findById(id);
        if (studentOptional.isPresent())
        {
            return new Response("Usuario encontrado", "200", Optional.of(studentOptional.get()));
        }
        return new Response("Usuario no encontrado", "404", Optional.empty());
    }
    public Response deleteById(Integer id)
    {
        usuarioRepository.deleteById(id);
        return new Response("Usuario eliminado", "200", Optional.empty());
    }
}
