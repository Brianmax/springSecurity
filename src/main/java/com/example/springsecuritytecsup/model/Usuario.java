package com.example.springsecuritytecsup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @Column(unique = true)
    private String email;
    @Size(min = 10)
    private String password;
    @OneToMany
    @JoinColumn(name = "student_id")
    Set<Nota> notas;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
