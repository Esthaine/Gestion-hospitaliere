package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Rendezvous extends AbstractEntity{

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User docteurSet;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime dateRendezVous;

    @Enumerated(EnumType.STRING)
    private RendezVousStatus status;

    public User getDocteurSet() {
        return docteurSet;
    }

    public void setDocteurSet(User docteurSet) {
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

    public RendezVousStatus getStatus() {
        return status;
    }

    public void setStatus(RendezVousStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Rendezvous{" +
                "person=" + person +
                ", docteurSet=" + docteurSet +
                ", dateRendezVous=" + dateRendezVous +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}