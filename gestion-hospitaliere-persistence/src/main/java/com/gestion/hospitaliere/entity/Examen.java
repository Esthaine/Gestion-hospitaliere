package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Examen extends AbstractEntity{

    private String description;
    private Date date;
    private String resultat;
    private String type;
    private String commentaires;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "examen_id", referencedColumnName = "id")
    private ResultatsExamens resultatExamens;

    @OneToOne
    private Docteur docteur;

    public Examen() {
    }

    public Docteur getDocteur() {
        return docteur;
    }

    public void setDocteur(Docteur docteur) {
        this.docteur = docteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public ResultatsExamens getResultatExamens() {
        return resultatExamens;
    }

    public void setResultatExamens(ResultatsExamens resultatExamens) {
        this.resultatExamens = resultatExamens;
    }
}
