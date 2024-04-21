package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.PrivilegeDao;
import com.gestion.hospitaliere.entity.Privilege;
import com.gestion.hospitaliere.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Transactional
public class PrivilegeDaoImpl extends JpaRepositoryImpl<Privilege> implements PrivilegeDao {

    public PrivilegeDaoImpl(Class<Privilege> clazz) {
        super(clazz);
    }

    @Override
    public Privilege findPrivilegeByName(String privilegeName) {
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findUserByName = entityManager.createQuery("From Privilege p where p.name=:privilegeName");
            findUserByName.setParameter("privilegeName", privilegeName);

            var resultList = findUserByName.getResultList();
            Privilege privilege = null;

            if (!resultList.isEmpty()) {
                privilege = (Privilege) resultList.get(0);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return privilege;
        }
    }
}
