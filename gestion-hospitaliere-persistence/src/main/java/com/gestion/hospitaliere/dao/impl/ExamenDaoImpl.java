package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.ExamenDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.Examen;
import com.gestion.hospitaliere.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class ExamenDaoImpl extends JpaRepositoryImpl<Examen> implements ExamenDao {

    public ExamenDaoImpl(Class<Examen> clazz) {
        super(clazz);
    }

    @Override
    public List<Examen> findByResultatExemens(Long id) {
        List<Examen> examenList = null;
        try (EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from Examen r where r.resultatExamens.id =: id order by r.createdAt desc");
            query.setParameter("id", id);
            if (query.getResultList() != null) {
                examenList = (List<Examen>) query.getResultList();
            }
        }
        return examenList;
    }
}
