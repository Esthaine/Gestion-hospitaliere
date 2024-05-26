package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.MedicamentDao;
import com.gestion.hospitaliere.entity.AntecedentMedical;
import com.gestion.hospitaliere.entity.Medicament;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class MedicamentDaoImpl extends JpaRepositoryImpl<Medicament> implements MedicamentDao {

    public MedicamentDaoImpl(Class<Medicament> clazz) {
        super(clazz);
    }

    @Override
    public List<Medicament> listMedicamentPerFiche(Long ficheId) {
        List<Medicament> medicaments = null;
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from Medicament m where m.fiche.id =:ficheId order by createdAt desc");
            query.setParameter("ficheId", ficheId);
            if (query.getResultList() != null) {
                medicaments = (List<Medicament>) query.getResultList();
            }
        }
        return medicaments;
    }
}
