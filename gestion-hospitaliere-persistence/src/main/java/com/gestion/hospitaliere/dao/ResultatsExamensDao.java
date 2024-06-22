package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.entity.ResultatsExamens;

import java.util.List;

public interface ResultatsExamensDao extends JpaRepository<ResultatsExamens>{


    List<ResultatsExamens> findFicheById(Long id);

}
