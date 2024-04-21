package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Privilege extends AbstractEntity{
    @Column(unique = true)
    private String name;
    private String description;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<RolePrivilege> rolePrivileges;

    public Privilege() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }
}
