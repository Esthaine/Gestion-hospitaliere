package com.gestion.hospitaliere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String eyeColor;
    private String nomUtilisateur;
    private String motDePasse;
    private String email;
    private String telephone;
    private LocalDate dateOfBirth;
    private String adresse;
    private String role;
    private LocalDate dateCreation;
    private LocalDate derniereConnexion;

    public User(Long id, String username, String eyeColor, String nomUtilisateur, String motDePasse, String email, String telephone, LocalDate dateOfBirth, String adresse, String role, LocalDate dateCreation, LocalDate derniereConnexion) {
        this.id = id;
        this.username = username;
        this.eyeColor = eyeColor;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.email = email;
        this.telephone = telephone;
        this.dateOfBirth = dateOfBirth;
        this.adresse = adresse;
        this.role = role;
        this.dateCreation = dateCreation;
        this.derniereConnexion = derniereConnexion;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDerniereConnexion() {
        return derniereConnexion;
    }

    public void setDerniereConnexion(LocalDate derniereConnexion) {
        this.derniereConnexion = derniereConnexion;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }
}
