package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.PremierSoinDao;
import com.gestion.hospitaliere.entity.PremierSoin;

public class PremierSoinDaoImpl extends JpaRepositoryImpl<PremierSoin> implements PremierSoinDao {

    public PremierSoinDaoImpl(Class<PremierSoin> clazz) {
        super(clazz);
    }

}
