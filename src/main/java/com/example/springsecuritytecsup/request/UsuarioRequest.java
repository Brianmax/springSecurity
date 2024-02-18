package com.example.springsecuritytecsup.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
