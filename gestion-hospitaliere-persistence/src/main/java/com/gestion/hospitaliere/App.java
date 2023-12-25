package com.gestion.hospitaliere;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.dao.FicheDao;
import com.gestion.hospitaliere.dao.PatientDao;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.entity.Fiche;
import com.gestion.hospitaliere.entity.Patient;
import com.gestion.hospitaliere.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )
    {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-hospitaliere-unit");
//        EntityManager em = emf.createEntityManager();
//        Patient patient = new Patient();
//
//        em.getTransaction().begin();
//        patient.setDateOfBirth(new Date());
//        patient.setGenre("F");
//        patient.setEmails("example@mqil.com");
//        em.persist(patient);
//        em.getTransaction().commit();

        Persistence persistence = new Persistence();
//        UserDao userDao = new UserDao(persistence);
//        FicheDao ficheDao = new FicheDao(persistence);
//        List<Fiche> fiches = ficheDao.findAll();
        PatientDao patientDao = new PatientDao(persistence);
        List<Patient> patients = patientDao.findAll();
//        List<User> users = userDao.findAll();

//        System.out.println("******************************");
//        System.out.println(users);
//        System.out.println("******************************");

//        System.out.println("************");
//        System.out.println(fiches);
//        System.out.println("************");

        System.out.println("************");
        System.out.println(patients);
        System.out.println("************");

    }
}
