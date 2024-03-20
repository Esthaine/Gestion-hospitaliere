package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.ResultatsExamensDao;
import com.gestion.hospitaliere.entity.ResultatsExamens;

public class ResultatExamenDaoImpl extends JpaRepositoryImpl<ResultatsExamens> implements ResultatsExamensDao {

    public ResultatExamenDaoImpl(Class<ResultatsExamens> clazz) {
        super(clazz);
    }
}
