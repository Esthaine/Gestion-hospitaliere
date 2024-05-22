package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.PersonDao;
import com.gestion.hospitaliere.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

public class PersonDaoImpl extends JpaRepositoryImpl<Person> implements PersonDao {

    public PersonDaoImpl(Class<Person> clazz) {
        super(clazz);
    }

    @Override
    public Person findByUserId(Long personId) {
        Person person = null;
        String sql =  "SELECT * FROM Person WHERE user_id = " + personId;
        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findPerson = entityManager.createNativeQuery(sql, Person.class);
            person = (Person) findPerson.getSingleResult();
        }
        return person;
    }
}
