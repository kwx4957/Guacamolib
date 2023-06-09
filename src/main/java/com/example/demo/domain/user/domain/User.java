package com.example.demo.domain.user.domain;

import com.example.demo.domain.user.dto.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private String provider;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Builder
    public User(String name, String email, String provider, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.provider = provider;
    }

    public User update(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
