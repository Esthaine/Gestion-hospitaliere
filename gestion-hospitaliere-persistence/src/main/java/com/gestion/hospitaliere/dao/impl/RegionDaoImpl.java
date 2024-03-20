package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.RegionDao;
import com.gestion.hospitaliere.entity.Region;

public class RegionDaoImpl extends JpaRepositoryImpl<Region> implements RegionDao {

    public RegionDaoImpl(Class<Region> clazz) {
        super(clazz);
    }
}
