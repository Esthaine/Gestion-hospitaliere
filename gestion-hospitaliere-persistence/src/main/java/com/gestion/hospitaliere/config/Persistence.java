package com.gestion.hospitaliere.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Persistence {
    public EntityManager entityManager(){
         EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit");
         return em.createEntityManager();
    }
}
