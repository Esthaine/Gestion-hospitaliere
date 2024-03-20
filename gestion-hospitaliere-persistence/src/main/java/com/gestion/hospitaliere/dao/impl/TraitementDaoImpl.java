package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.TraitementDao;
import com.gestion.hospitaliere.entity.Traitement;

public class TraitementDaoImpl extends JpaRepositoryImpl<Traitement> implements TraitementDao {

    public TraitementDaoImpl(Class<Traitement> clazz) {
        super(clazz);
    }
}
