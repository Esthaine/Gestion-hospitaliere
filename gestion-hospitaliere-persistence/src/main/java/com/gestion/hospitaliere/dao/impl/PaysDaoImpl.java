package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.PaysDao;
import com.gestion.hospitaliere.entity.Pays;

public class PaysDaoImpl extends JpaRepositoryImpl<Pays> implements PaysDao {

    public PaysDaoImpl(Class<Pays> clazz) {
        super(clazz);
    }
}
