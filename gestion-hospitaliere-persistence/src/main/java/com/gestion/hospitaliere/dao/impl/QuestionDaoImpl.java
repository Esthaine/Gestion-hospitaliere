package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.QuestionDao;
import com.gestion.hospitaliere.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class QuestionDaoImpl extends JpaRepositoryImpl<Question> implements QuestionDao {

    public QuestionDaoImpl(Class<Question> clazz) {
        super(clazz);
    }

    @Override
    public List<Question> findByResultExamens(Long id) {
        List<Question> questionList = null;
        try (EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from Question r where r.resultatsExamens.id =: id order by r.createdAt desc");
            query.setParameter("id", id);
            if (query.getResultList() != null) {
                questionList = (List<Question>) query.getResultList();
            }
        }
        return questionList;
    }
}
