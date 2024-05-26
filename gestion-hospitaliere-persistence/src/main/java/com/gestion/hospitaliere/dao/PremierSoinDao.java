package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.entity.PremierSoin;

import java.util.List;

public interface PremierSoinDao extends JpaRepository<PremierSoin>{
    List<PremierSoin> findByFiche(Long id);
}
