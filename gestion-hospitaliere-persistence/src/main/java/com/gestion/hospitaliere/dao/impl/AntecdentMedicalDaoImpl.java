package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.AntecedentMedicalDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.AntecedentMedical;

public class AntecdentMedicalDaoImpl extends JpaRepositoryImpl<AntecedentMedical> implements AntecedentMedicalDao {

    public AntecdentMedicalDaoImpl(Class<AntecedentMedical> clazz) {
        super(clazz);
    }
}
