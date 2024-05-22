package com.gestion.hospitaliere.entity;


import jakarta.persistence.*;

@Embeddable
public class Address {

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

    public Address() {
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
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
