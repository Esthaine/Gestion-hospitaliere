package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.QuestionDao;
import com.gestion.hospitaliere.entity.Question;

public class QuestionDaoImpl extends JpaRepositoryImpl<Question> implements QuestionDao {

    public QuestionDaoImpl(Class<Question> clazz) {
        super(clazz);
    }
}
