package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.ResultatsExamensDao;
import com.gestion.hospitaliere.entity.ResultatsExamens;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class ResultatExamenDaoImpl extends JpaRepositoryImpl<ResultatsExamens> implements ResultatsExamensDao {

    public ResultatExamenDaoImpl(Class<ResultatsExamens> clazz) {
        super(clazz);
    }

    @Override
    public List<ResultatsExamens> findFicheById(Long id) {

        List<ResultatsExamens> resultatsExamens = null;
        try (EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from ResultatsExamens r where r.fiche.id =: id order by r.createdAt desc");
            query.setParameter("id", id);
            if (query.getResultList() != null) {
                resultatsExamens = (List<ResultatsExamens>) query.getResultList();
            }
        }
        return resultatsExamens;
    }
}
