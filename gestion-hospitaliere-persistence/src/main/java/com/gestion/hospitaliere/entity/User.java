package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String motDePasse;
    @Column(nullable = false, unique = true)
    private String email;
    private Date dateCreation;
    private Date derniereConnexion;

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDerniereConnexion() {
        return derniereConnexion;
    }

    public void setDerniereConnexion(Date derniereConnexion) {
        this.derniereConnexion = derniereConnexion;
    }

    public User() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
