package com.gestion.hospitaliere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class Role extends AbstractEntity{

    private String name;
    private String description;

    @OneToMany
    private Set<Privilege> privileges;

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

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
