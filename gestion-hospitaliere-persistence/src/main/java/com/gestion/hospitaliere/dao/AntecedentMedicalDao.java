package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.AntecedentMedical;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public interface AntecedentMedicalDao extends JpaRepository<AntecedentMedical>{

    List<AntecedentMedical> antecedentMedicalFindByFiche(Long idAntecedent);

}
