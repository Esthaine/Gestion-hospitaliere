package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Fiche;
import jakarta.persistence.Query;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FicheDao implements JpaRepository<Fiche>{

    private Persistence persistence;

    public FicheDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Fiche save(Fiche fiche) {
        try{

            if (fiche != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(fiche);
                persistence.entityManager().getTransaction().commit();
                return fiche;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Fiche> findAll() {
        Query query = persistence.entityManager().createQuery("Select f From Fiche f");
        return query.getResultList();
    }

    @Override
    public Fiche findById(Long id) {
        try{
            Fiche fiche = persistence.entityManager().find(Fiche.class, id);
            if (fiche != null)
                return fiche;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Fiche deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Fiche> saveAll(Fiche... fiches) {
        List<Fiche> fiches1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(fiches).forEach(fiche -> {
                persistence.entityManager().persist(fiche);
                fiches1.add(fiche);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return fiches1;
    }

    @Override
    public List<Fiche> deleteMany(Long... ids) {
        List<Fiche> fiches = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Fiche fiche = findById(id);
                if (fiche != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete f From Fiche where f.id");
                    deleteQuery.setParameter("id", id);
                    int deletedFiche = deleteQuery.getFirstResult();
                    if (deletedFiche > 0){
                        fiches.add(fiche);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return fiches;
    }

    @Override
    public List<Fiche> deleteMany(Fiche... fiches) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
