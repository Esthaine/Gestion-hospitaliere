package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.MedicamentDao;
import com.gestion.hospitaliere.entity.Medicament;

public class MedicamentDaoImpl extends JpaRepositoryImpl<Medicament> implements MedicamentDao {

    public MedicamentDaoImpl(Class<Medicament> clazz) {
        super(clazz);
    }
}
