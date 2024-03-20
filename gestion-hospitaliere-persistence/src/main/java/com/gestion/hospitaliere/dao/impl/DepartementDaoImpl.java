package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.DepartementDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.Departement;

public class DepartementDaoImpl extends JpaRepositoryImpl<Departement> implements DepartementDao {

    public DepartementDaoImpl(Class<Departement> clazz) {
        super(clazz);
    }
}
