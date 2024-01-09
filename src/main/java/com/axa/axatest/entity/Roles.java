package com.axa.axatest.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles",schema = "dbo")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Permission> permissions;

}
