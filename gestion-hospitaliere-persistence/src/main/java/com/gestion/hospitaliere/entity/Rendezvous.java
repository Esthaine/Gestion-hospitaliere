package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Rendezvous extends AbstractEntity{

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Docteur> docteurSet;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime dateRendezVous;

    public Set<Docteur> getDocteurSet() {
        return docteurSet;
    }

    public void setDocteurSet(Set<Docteur> docteurSet) {
        this.docteurSet = docteurSet;
    }

    public LocalDateTime getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(LocalDateTime dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}