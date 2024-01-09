package com.axa.axatest.entity;

import com.axa.axatest.enumeration.PermissionType;
import jakarta.persistence.*;

@Entity
@Table(name = "permission",schema = "dbo")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permssionId;

    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;

    @ManyToOne
    private Roles roles;


}
