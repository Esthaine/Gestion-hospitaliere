package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Role extends AbstractEntity{

    private String name;
    private String description;


    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<User> user = new HashSet<>();


    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<RolePrivilege> privileges = new HashSet<>();

    public Role() {
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

    public Set<RolePrivilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<RolePrivilege> privileges) {
        this.privileges = privileges;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", privileges=" + privileges +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
