package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;


@Entity
public class Symptomes extends AbstractEntity{

//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "analyze_id", referencedColumnName = "id")
//    private Analyse analyse;

    private String description;

    private String gravite;

    private String type;

    private String duree;

    private String traitement;

    public Symptomes() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

//    public Analyse getAnalyse() {
//        return analyse;
//    }
//
//    public void setAnalyse(Analyse analyse) {
//        this.analyse = analyse;
//    }
}
