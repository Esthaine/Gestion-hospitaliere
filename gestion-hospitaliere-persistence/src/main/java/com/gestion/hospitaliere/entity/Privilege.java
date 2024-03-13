package com.gestion.hospitaliere.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Privilege extends AbstractEntity{
    private String name;
    private String description;

    @ManyToOne
    private Role role;

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

    public Role getRole() {
        return role;
    }
}
