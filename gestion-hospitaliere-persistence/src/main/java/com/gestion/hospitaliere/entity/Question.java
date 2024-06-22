package com.gestion.hospitaliere.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question extends AbstractEntity{

    private String titre;

    private String appreciation;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(columnDefinition = "result_id", referencedColumnName = "id")
    private ResultatsExamens resultatsExamens;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public ResultatsExamens getResultatsExamens() {
        return resultatsExamens;
    }

    public void setResultatsExamens(ResultatsExamens resultatsExamens) {
        this.resultatsExamens = resultatsExamens;
    }
}
