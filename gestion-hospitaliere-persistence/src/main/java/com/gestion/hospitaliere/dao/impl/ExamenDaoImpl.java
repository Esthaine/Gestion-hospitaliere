package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.ExamenDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.Examen;

public class ExamenDaoImpl extends JpaRepositoryImpl<Examen> implements ExamenDao {

    public ExamenDaoImpl(Class<Examen> clazz) {
        super(clazz);
    }
}
