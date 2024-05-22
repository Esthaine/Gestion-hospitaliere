package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.DepartementDao;
import com.gestion.hospitaliere.dao.impl.DepartementDaoImpl;
import com.gestion.hospitaliere.entity.Departement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DepartmentTest {

    Class<Departement> departmentClass;
    DepartementDao departementDao;
    @BeforeEach
    void setUp() throws ClassNotFoundException {
        departmentClass =  (Class<Departement>) Class.forName("com.gestion.hospitaliere.entity.Departement");
        departementDao = new DepartementDaoImpl(departmentClass);
    }


    @Test
    void createDepartment(){
        Departement departement = departementDao.save(newDepartement());
        assertNotNull(departement);
    }

    Departement newDepartement(){
        Departement departement = new Departement();
        departement.setNomDepartement("Radiologie et imageries");
        departement.setManager(null);
        departement.setDescription("Radio");
        return departement;
    }

}
