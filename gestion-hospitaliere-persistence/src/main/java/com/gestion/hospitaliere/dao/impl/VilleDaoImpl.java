package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.VilleDao;
import com.gestion.hospitaliere.entity.Ville;

public class VilleDaoImpl extends JpaRepositoryImpl<Ville> implements VilleDao {

    public VilleDaoImpl(Class<Ville> clazz) {
        super(clazz);
    }
}
