package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Symptomes;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SymptomesDao implements JpaRepository<Symptomes>{

    private Persistence persistence;

    public SymptomesDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Symptomes save(Symptomes symptomes) {
        try{

            if (symptomes != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(symptomes);
                persistence.entityManager().getTransaction().commit();
                return symptomes;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Symptomes> findAll() {
        Query query = persistence.entityManager().createQuery("Select s From Symptomes s");
        return query.getResultList();
    }

    @Override
    public Symptomes findById(Long id) {
        try{
            Symptomes symptomes = persistence.entityManager().find(Symptomes.class, id);
            if (symptomes != null)
                return symptomes;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Symptomes deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Symptomes> saveAll(Symptomes... symptomess) {
        List<Symptomes> symptomes1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(symptomess).forEach(symptomes -> {
                persistence.entityManager().persist(symptomes);
                symptomes1.add(symptomes);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return symptomes1;
    }

    @Override
    public List<Symptomes> deleteMany(Long... ids) {
        List<Symptomes> symptomess = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Symptomes symptomes = findById(id);
                if (symptomes != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete s From Symptomes where s.id");
                    deleteQuery.setParameter("id", id);
                    int deletedSymptomes = deleteQuery.getFirstResult();
                    if (deletedSymptomes > 0){
                        symptomess.add(symptomes);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return symptomess;
    }

    @Override
    public List<Symptomes> deleteMany(Symptomes... symptomes) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
