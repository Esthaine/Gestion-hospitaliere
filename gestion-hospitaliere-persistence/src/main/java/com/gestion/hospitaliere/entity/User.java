package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String motDePasse;
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToOne(cascade =  CascadeType.MERGE, fetch = FetchType.LAZY)
    private Person person;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "analyze_id", referencedColumnName = "id", nullable = true)
//    private Analyse analyse;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Departement departement;

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

    public User() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

//    public Analyse getAnalyse() {
//        return analyse;
//    }
//
//    public void setAnalyse(Analyse analyse) {
//        this.analyse = analyse;
//    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
