package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.DepartementDao;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.dao.impl.DepartementDaoImpl;
import com.gestion.hospitaliere.dao.impl.UserDaoImpl;
import com.gestion.hospitaliere.entity.Departement;
import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.utils.PersistenceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DepartmentTest {

    Class<Departement> departmentClass;
    DepartementDao departementDao;
    UserDao userDao;
    Class<User> userClass;

    @BeforeEach
    void setUp() throws ClassNotFoundException {
        departmentClass =  (Class<Departement>) Class.forName("com.gestion.hospitaliere.entity.Departement");
        departementDao = new DepartementDaoImpl(departmentClass);
        userClass = (Class<User>) Class.forName("com.gestion.hospitaliere.entity.User");
        userDao = new UserDaoImpl(userClass);
    }


    @Test
    void createDepartment(){
        Departement departement = departementDao.save(newDepartement());
        assertNotNull(departement);
    }


    //@Test
    void newDepartmentTestCreation(){

        Departement departement = new Departement();
        departement.setManager(userDao.findById(4L));
        departement.setNomDepartement("Oncologie");
        departement.setCode(PersistenceUtils.departmentNumberGeneration());
        departement.setDescription("Oncologie Description");
        departementDao.save(departement);
    }

    Departement newDepartement(){
        Departement departement = departementDao.findById(1L);
        departement.setManager(userDao.findById(6L));
        departement.setCode(PersistenceUtils.departmentNumberGeneration());
        return departement;
    }




}
