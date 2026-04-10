package com.springboot.security.entities;


import com.springboot.security.entities.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data   // Lombok - Substitui algumas anotações
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String pass;
    //    @Column(unique = true) // Define que o e-mail é único
    private String email;
    @Enumerated(EnumType.STRING) // Guarda o ENUM em string no DB em vez de nº ordinais
    private Role role;

}