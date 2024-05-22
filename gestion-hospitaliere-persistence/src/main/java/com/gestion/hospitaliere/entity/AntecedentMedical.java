package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class AntecedentMedical extends AbstractEntity{

    private String description;

    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    private String type;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(columnDefinition = "fiche_id", referencedColumnName = "id")
    private Fiche fiche;

    public AntecedentMedical() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Fiche getFiche() {
        return fiche;
    }

    public void setFiche(Fiche fiche) {
        this.fiche = fiche;
    }
}
