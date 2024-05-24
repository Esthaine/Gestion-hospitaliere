package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Fiche;
import jakarta.persistence.Query;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public interface FicheDao extends JpaRepository<Fiche>{

    Fiche findByPatient(Long id);
}
