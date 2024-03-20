package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.DocteurDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.Docteur;

public class DocteurDaoImpl extends JpaRepositoryImpl<Docteur> implements DocteurDao {

    public DocteurDaoImpl(Class<Docteur> clazz) {
        super(clazz);
    }
}
