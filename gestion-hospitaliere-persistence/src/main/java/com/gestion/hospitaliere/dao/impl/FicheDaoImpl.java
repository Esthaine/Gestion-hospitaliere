package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.FicheDao;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.entity.Fiche;

public class FicheDaoImpl extends JpaRepositoryImpl<Fiche> implements FicheDao {

    public FicheDaoImpl(Class<Fiche> clazz) {
        super(clazz);
    }
}
