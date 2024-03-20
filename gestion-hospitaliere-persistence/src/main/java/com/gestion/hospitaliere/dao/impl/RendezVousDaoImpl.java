package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.RendezVousDao;
import com.gestion.hospitaliere.entity.Rendezvous;

public class RendezVousDaoImpl extends JpaRepositoryImpl<Rendezvous> implements RendezVousDao {

    public RendezVousDaoImpl(Class<Rendezvous> clazz) {
        super(clazz);
    }
}
