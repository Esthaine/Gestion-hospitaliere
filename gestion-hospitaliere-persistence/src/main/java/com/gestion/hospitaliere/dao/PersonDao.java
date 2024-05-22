package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Person;
import com.gestion.hospitaliere.entity.User;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public interface PersonDao extends JpaRepository<Person>{

    Person findByUserId(Long personId);
}
