package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Examen;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;


public class ExamenDao implements JpaRepository<Examen>{

    private Persistence persistence;

    public ExamenDao(Persistence persistence) {
        this.persistence = persistence;
    }
        @Override
    public Examen save(Examen examen) {
            try{

                if (examen != null){
                    persistence.entityManager().getTransaction().begin();
                    persistence.entityManager().persist(examen);
                    persistence.entityManager().getTransaction().commit();
                    return examen;
                }
            }catch (Exception e){
                System.out.printf("Message : " + e.getMessage());
            }
            return null;
    }

    @Override
    public List<Examen> findAll() {
        Query query = persistence.entityManager().createQuery("Select e From Examen e");
        return query.getResultList();
    }

    @Override
    public Examen findById(Long id) {
        try{
            Examen examen = persistence.entityManager().find(Examen.class, id);
            if (examen != null)
                return examen;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Examen deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Examen> saveAll(Examen... examens) {
        List<Examen> examens1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(examens).forEach(examen -> {
                persistence.entityManager().persist(examen);
                examens1.add(examen);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return examens1;
    }

    @Override
    public List<Examen> deleteMany(Long... ids) {
        List<Examen> examens = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Examen examen = findById(id);
                if (examen != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete e From Examen where e.id");
                    deleteQuery.setParameter("id", id);
                    int deletedExamen = deleteQuery.getFirstResult();
                    if (deletedExamen > 0){
                        examens.add(examen);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return examens;
    }

    @Override
    public List<Examen> deleteMany(Examen... examen) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
