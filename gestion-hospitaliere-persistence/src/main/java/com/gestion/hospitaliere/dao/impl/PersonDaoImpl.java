package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.PersonDao;
import com.gestion.hospitaliere.entity.Person;

public class PersonDaoImpl extends JpaRepositoryImpl<Person> implements PersonDao {

    public PersonDaoImpl(Class<Person> clazz) {
        super(clazz);
    }
}
