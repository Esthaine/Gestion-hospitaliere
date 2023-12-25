package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Decision;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;


import java.util.List;

public class DecisionDao implements JpaRepository<Decision>{
    private Persistence persistence;

    public DecisionDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Decision save(Decision decision) {
        try{

            if (decision != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(decision);
                persistence.entityManager().getTransaction().commit();
                return decision;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Decision> findAll() {
        Query query = persistence.entityManager().createQuery("Select d From Decision d");
        return query.getResultList();
    }

    @Override
    public Decision findById(Long id) {
        try{
            Decision decision = persistence.entityManager().find(Decision.class, id);
            if (decision != null)
                return decision;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Decision deleteById(Long id) {
        try{

        }catch (Exception e){
                System.out.printf("Message: " + e.getMessage());
        }
            return null;
    }

    @Override
    public List<Decision> saveAll(Decision... decisions) {
        List<Decision> decision1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(decisions).forEach(decision -> {
                persistence.entityManager().persist(decision);
                decision1.add(decision);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return decision1;

    }

    @Override
    public List<Decision> deleteMany(Long... ids) {
        List<Decision> decisions = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Decision decision = findById(id);
                if (decision != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete d From Decision where d.id");
                    deleteQuery.setParameter("id", id);
                    int deletedDecision = deleteQuery.getFirstResult();
                    if (deletedDecision > 0){
                        decisions.add(decision);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return decisions;

    }

    @Override
    public List<Decision> deleteMany(Decision... decisions) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }
}
