package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AntecedentMedical extends AbstractEntity{
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String type;

    public AntecedentMedical() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
