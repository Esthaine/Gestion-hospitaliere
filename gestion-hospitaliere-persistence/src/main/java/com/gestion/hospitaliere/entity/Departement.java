package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Departement extends AbstractEntity{

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User manager;

    @Column(unique = true)
    private String NomDepartement;
    @Column(unique = true)
    private String code;
    private String Description;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<User> users;

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getNomDepartement() {
        return NomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        NomDepartement = nomDepartement;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
