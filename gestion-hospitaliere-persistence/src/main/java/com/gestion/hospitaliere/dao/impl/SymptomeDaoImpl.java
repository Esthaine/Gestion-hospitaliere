package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.SymptomesDao;
import com.gestion.hospitaliere.entity.Symptomes;

public class SymptomeDaoImpl extends JpaRepositoryImpl<Symptomes> implements SymptomesDao {

    public SymptomeDaoImpl(Class<Symptomes> clazz) {
        super(clazz);
    }
}
