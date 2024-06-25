package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.RoleDao;
import com.gestion.hospitaliere.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;


@Transactional
public class RoleDaoImpl extends JpaRepositoryImpl<Role> implements RoleDao {
    public RoleDaoImpl(Class<Role> clazz) {
        super(clazz);
    }

    @Override
    public Role findRoleByName(String roleName) {
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findUserByName = entityManager.createQuery("From Role r where r.name=:roleName");
            findUserByName.setParameter("roleName", roleName);

            var resultList = findUserByName.getResultList();
            Role role = null;

            if (!resultList.isEmpty()) {
                role = (Role) resultList.get(0);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return role;
        }
    }

    @Override
    public List<Role> findRoleByUserNameDifferentDe(String roleName) {
        List<Role> roles = null;
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findUserByName = entityManager.createQuery("From Role r where r.name!=:roleName or r.name!=:roleName2");
            findUserByName.setParameter("roleName", roleName);
            findUserByName.setParameter("roleName2", "ADMIN");

            roles = findUserByName.getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return roles;
        }
    }
}
