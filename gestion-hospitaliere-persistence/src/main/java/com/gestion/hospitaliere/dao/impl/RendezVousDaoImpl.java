package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.RendezVousDao;
import com.gestion.hospitaliere.entity.RendezVousStatus;
import com.gestion.hospitaliere.entity.Rendezvous;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class RendezVousDaoImpl extends JpaRepositoryImpl<Rendezvous> implements RendezVousDao {

    public RendezVousDaoImpl(Class<Rendezvous> clazz) {
        super(clazz);
    }

    @Override
    public List<Rendezvous> findByPatient(Long id) {
        List<Rendezvous> rendezvousList = null;
        String sql =  "SELECT * FROM RendezVous WHERE  person_id= " + id;
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findPerson = entityManager.createNativeQuery(sql, Rendezvous.class);
            rendezvousList = findPerson.getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        return rendezvousList;
    }

    @Override
    public List<Rendezvous> findByDoctor(Long id, RendezVousStatus status) {
        List<Rendezvous> rendezvousList = null;
        String sql =  "SELECT * FROM RendezVous WHERE  doctor_id = " + id + " AND status = \'"+ status.toString() + "\' ORDER BY id desc";
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findPerson = entityManager.createNativeQuery(sql, Rendezvous.class);
            if (findPerson.getResultList() != null) {
                rendezvousList = findPerson.getResultList();
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        return rendezvousList;
    }

    @Override
    public List<Rendezvous> findByStatus(RendezVousStatus status) {
        List<Rendezvous> rendezvousList = null;
        String sql =  "SELECT * FROM RendezVous WHERE status = \'"+ status.toString() + "\' ORDER BY id desc";
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findPerson = entityManager.createNativeQuery(sql, Rendezvous.class);
            if (findPerson.getResultList() != null) {
                rendezvousList = findPerson.getResultList();
            }
        }
        return rendezvousList;
    }

}
