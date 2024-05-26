package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.PremierSoinDao;
import com.gestion.hospitaliere.entity.PremierSoin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class PremierSoinDaoImpl extends JpaRepositoryImpl<PremierSoin> implements PremierSoinDao {

    public PremierSoinDaoImpl(Class<PremierSoin> clazz) {
        super(clazz);
    }

    @Override
    public List<PremierSoin> findByFiche(Long id) {
        List<PremierSoin> premierSoins = null;
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from PremierSoin p where p.fiche.id =: id order by createdAt desc");
            query.setParameter("id", id);
            if (query.getResultList() != null) {
                premierSoins = (List<PremierSoin>) query.getResultList();
            }
        }
        return premierSoins;
    }
}
