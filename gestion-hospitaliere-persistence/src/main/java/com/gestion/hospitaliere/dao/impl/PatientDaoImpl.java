package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.PatientDao;
import com.gestion.hospitaliere.entity.Patient;

public  class PatientDaoImpl extends JpaRepositoryImpl<Patient> implements PatientDao {

    public PatientDaoImpl(Class<Patient> clazz) {
        super(clazz);
    }
}
