package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class DemandeExamen extends AbstractEntity{

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Question> questions;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(columnDefinition = "demand_id", referencedColumnName = "id")
    private ResultatsExamens resultatsExamens;


    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public ResultatsExamens getResultatsExamens() {
        return resultatsExamens;
    }

    public void setResultatsExamens(ResultatsExamens resultatsExamens) {
        this.resultatsExamens = resultatsExamens;
    }
}
