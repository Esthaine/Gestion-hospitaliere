package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.DecisionDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.Decision;

public class DecisionDaoImpl extends JpaRepositoryImpl<Decision> implements DecisionDao {

    public DecisionDaoImpl(Class<Decision> clazz) {
        super(clazz);
    }
}
