package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.FicheDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.Fiche;
import com.gestion.hospitaliere.entity.Person;
import com.gestion.hospitaliere.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class FicheDaoImpl extends JpaRepositoryImpl<Fiche> implements FicheDao {

    public FicheDaoImpl(Class<Fiche> clazz) {
        super(clazz);
    }

    @Override
    public Fiche findByPatient(Long id) {
        Fiche fiche = null;
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findFiche = entityManager.createQuery("From Fiche f where f.patient.id =:id");
            findFiche.setParameter("id", id);
            var resultList =  findFiche.getResultList();
            if (resultList != null && !resultList.isEmpty()) {
                fiche = (Fiche) resultList.get(0);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        return fiche;
    }

    @Override
    public Fiche getFicheByFicheNumber(String ficheNumber) {
        Fiche fiche = null;
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findFiche = entityManager.createQuery("From Fiche f where f.ficheNumber =:ficheNumber");
            findFiche.setParameter("ficheNumber", ficheNumber);
            var resultList =  findFiche.getResultList();
            if (resultList != null && !resultList.isEmpty()) {
                fiche = (Fiche) resultList.get(0);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        return fiche;
    }
}
