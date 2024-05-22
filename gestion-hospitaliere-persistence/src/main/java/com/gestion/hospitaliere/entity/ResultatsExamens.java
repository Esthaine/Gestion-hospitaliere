package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import javax.swing.*;
import java.util.Set;

@Entity
public class ResultatsExamens extends AbstractEntity {

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<DemandeExamen> demandeExamens;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Examen> examen;

    private String valeur;

    private String unite;

    private String interpretation;

    private String reference;

    public ResultatsExamens() {
    }

    public Set<DemandeExamen> getDemandeExamens() {
        return demandeExamens;
    }

    public void setDemandeExamens(Set<DemandeExamen> demandeExamens) {
        this.demandeExamens = demandeExamens;
    }

    public Set<Examen> getExamen() {
        return examen;
    }

    public void setExamen(Set<Examen> examen) {
        this.examen = examen;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
