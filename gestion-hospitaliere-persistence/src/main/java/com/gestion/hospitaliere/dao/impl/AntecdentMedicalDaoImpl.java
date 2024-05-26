package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.AntecedentMedicalDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.AntecedentMedical;
import com.gestion.hospitaliere.entity.PremierSoin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class AntecdentMedicalDaoImpl extends JpaRepositoryImpl<AntecedentMedical> implements AntecedentMedicalDao {

    public AntecdentMedicalDaoImpl(Class<AntecedentMedical> clazz) {
        super(clazz);
    }

    @Override
    public List<AntecedentMedical> antecedentMedicalFindByFiche(Long idAntecedent) {
        List<AntecedentMedical> antecedentMedicals = null;
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from AntecedentMedical a where a.fiche.id =: idAntecedent order by createdAt desc");
            query.setParameter("idAntecedent", idAntecedent);
            if (query.getResultList() != null) {
                antecedentMedicals = (List<AntecedentMedical>) query.getResultList();
            }
        }
        return antecedentMedicals;
    }
}
