package com.xaverry.labo_1_jose_chaverri.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}