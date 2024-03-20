package com.gestion.hospitaliere.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Persistence {
    public Persistence() {
    }
    public EntityManager entityManager(){
        try (EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            return em.createEntityManager();
        }
    }
}
