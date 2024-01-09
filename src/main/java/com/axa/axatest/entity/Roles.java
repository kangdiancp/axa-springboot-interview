package com.axa.axatest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "roles",schema = "dbo")
@RequiredArgsConstructor
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long roleId;
    private final String roleName;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Permission> permissions;

}
