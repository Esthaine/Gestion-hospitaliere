package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Examen;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;


public interface ExamenDao extends JpaRepository<Examen>{
}
