package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.DepartementDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.Departement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

public class DepartementDaoImpl extends JpaRepositoryImpl<Departement> implements DepartementDao {

    public DepartementDaoImpl(Class<Departement> clazz) {
        super(clazz);
    }

    @Override
    public Departement findByCodeOrName(String value) {
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findByCodeOrName = entityManager.createQuery("From Departement d where d.NomDepartement =:value or d.code=:value");
            findByCodeOrName.setParameter("value", value);
            var resultList = findByCodeOrName.getResultList();
            Departement departement = null;

            if (!resultList.isEmpty()) {
                departement = (Departement) resultList.get(0);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return departement;
        }
    }
}
