package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Person extends AbstractEntity{

    private String firstName;

    private String lastName;

    private String givenName;

    private Date dateOfBirth;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "fiche_id", referencedColumnName = "id", nullable = true, unique = true)
    private Fiche fiche;

    @OneToOne(cascade =  {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String streetName;

    private String houseNumber;

    private String township;

    @ManyToOne(cascade =   CascadeType.MERGE , fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "pays_id", referencedColumnName = "id")
    private Pays pays;

    @ManyToOne(cascade =  CascadeType.MERGE , fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "region_id", referencedColumnName = "id")
    private Region region;

    @ManyToOne(cascade =  CascadeType.MERGE , fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "ville_id", referencedColumnName = "id")
    private Ville ville;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fiche getFiche() {
        return fiche;
    }

    public void setFiche(Fiche fiche) {
        this.fiche = fiche;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}
