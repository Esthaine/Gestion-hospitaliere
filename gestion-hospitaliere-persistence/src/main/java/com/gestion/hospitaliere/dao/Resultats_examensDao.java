package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Resultats_examens;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Resultats_examensDao implements JpaRepository<Resultats_examens>{

    private Persistence persistence;

    public Resultats_examensDao(Persistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public Resultats_examens save(Resultats_examens resultats_examens) {
        try{

            if (resultats_examens != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(resultats_examens);
                persistence.entityManager().getTransaction().commit();
                return resultats_examens;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Resultats_examens> findAll() {
        Query query = persistence.entityManager().createQuery("Select res From Resultats_examens res");
        return query.getResultList();
    }

    @Override
    public Resultats_examens findById(Long id) {
        try{
            Resultats_examens resultats_examens = persistence.entityManager().find(Resultats_examens.class, id);
            if (resultats_examens != null)
                return resultats_examens;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Resultats_examens deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Resultats_examens> saveAll(Resultats_examens... resultats_examenss) {
        List<Resultats_examens> resultats_examens1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(resultats_examenss).forEach(resultats_examens -> {
                persistence.entityManager().persist(resultats_examens);
                resultats_examens1.add(resultats_examens);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return resultats_examens1;
    }

    @Override
    public List<Resultats_examens> deleteMany(Long... ids) {
        List<Resultats_examens> resultats_examens = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Resultats_examens resultatsExamens = findById(id);
                if (resultats_examens != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete res From Resultats_examens where u.id");
                    deleteQuery.setParameter("id", id);
                    int deletedResultats_examens = deleteQuery.getFirstResult();
                    if (deletedResultats_examens > 0){
                        resultats_examens.add(resultatsExamens);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return resultats_examens;
    }

    @Override
    public List<Resultats_examens> deleteMany(Resultats_examens... resultats_examens) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
