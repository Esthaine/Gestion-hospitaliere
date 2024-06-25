package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

@Entity
public class Examen extends AbstractEntity{

    private String resultat;
    private String type;
    private String commentaires;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "examen_id", referencedColumnName = "id")
    private ResultatsExamens resultatExamens;

    public Examen() {
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
