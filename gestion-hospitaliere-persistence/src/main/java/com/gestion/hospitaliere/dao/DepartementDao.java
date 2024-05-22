package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.entity.Departement;

public interface DepartementDao extends JpaRepository<Departement>{

    Departement findByCodeOrName(String code);
}
