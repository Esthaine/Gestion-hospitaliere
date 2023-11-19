package com.gestion.hospitaliere.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;

@Entity
public class Docteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
