package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.FicheDao;
import com.gestion.hospitaliere.dao.PersonDao;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.dao.impl.FicheDaoImpl;
import com.gestion.hospitaliere.dao.impl.PersonDaoImpl;
import com.gestion.hospitaliere.dao.impl.UserDaoImpl;
import com.gestion.hospitaliere.entity.*;
import com.gestion.hospitaliere.utils.PersistenceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateFicheTest {

    private Class<Fiche> ficheClass;
    private Class<User> userClass;
    private Class<Person> personClass;


    private FicheDao ficheDao;
    private PersonDao personDao;
    private UserDao userDao;

    @BeforeEach
    void setUp() throws ClassNotFoundException {
        ficheClass = (Class<Fiche>) Class.forName("com.gestion.hospitaliere.entity.Fiche");
        userClass = (Class<User>) Class.forName("com.gestion.hospitaliere.entity.User");
        personClass = (Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person");

        ficheDao = new FicheDaoImpl(ficheClass);
        personDao = new PersonDaoImpl(personClass);
        userDao = new UserDaoImpl(userClass);
    }


    @Test
    void ficheCreation(){

        Fiche fiche = new Fiche();
        fiche.setFicheNumber("MMY" + PersistenceUtils.getRandomNumberString());
        fiche.setGenre(Genre.MASCULIN);
        fiche.setStatus(FicheStatus.ACTIVE);
        fiche.setCreatedBy(userDao.findById(3L));
        fiche.setPatient(personDao.findById(37L));
        ficheDao.save(fiche);

        assertNotNull(fiche);
    }

    @Test
    void findFicheByPatient() throws Exception{
//        Fiche fiche = ficheDao.findByPatient(37L);
//        assertNotNull(fiche);
        //assertNull(fiche);

        System.out.println("Number of fiche: " + ficheDao.count());
    }
}
