package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Decision;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;


import java.util.List;

public interface DecisionDao extends JpaRepository<Decision>{
}
