package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Patient;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PatientDao implements JpaRepository<Patient>{

    private Persistence persistence;

    public PatientDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Patient save(Patient patient) {
        try{

            if (patient != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(patient);
                persistence.entityManager().getTransaction().commit();
                return patient;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> findAll() {
        Query query = persistence.entityManager().createQuery("Select p From Patient p");
        return query.getResultList();
    }

    @Override
    public Patient findById(Long id) {
        try{
            Patient patient = persistence.entityManager().find(Patient.class, id);
            if (patient != null)
                return patient;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Patient deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> saveAll(Patient... patients) {
        List<Patient> patients1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(patients).forEach(patient -> {
                persistence.entityManager().persist(patient);
                patients1.add(patient);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return patients1;
    }

    @Override
    public List<Patient> deleteMany(Long... ids) {
        List<Patient> patients = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Patient patient = findById(id);
                if (patient != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete p From Patient where p.id");
                    deleteQuery.setParameter("id", id);
                    int deletedPatient = deleteQuery.getFirstResult();
                    if (deletedPatient > 0){
                        patients.add(patient);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return patients;
    }

    @Override
    public List<Patient> deleteMany(Patient... patients) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
