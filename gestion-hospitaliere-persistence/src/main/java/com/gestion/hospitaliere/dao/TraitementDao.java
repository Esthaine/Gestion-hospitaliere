package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Traitement;
import jakarta.persistence.Query;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class TraitementDao implements JpaRepository<Traitement>{

    private Persistence persistence;

    public TraitementDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Traitement save(Traitement traitement) {
        try{

            if (traitement != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(traitement);
                persistence.entityManager().getTransaction().commit();
                return traitement;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Traitement> findAll() {
        Query query = persistence.entityManager().createQuery("Select t From Traitement t");
        return query.getResultList();
    }

    @Override
    public Traitement findById(Long id) {
        try{
            Traitement traitement = persistence.entityManager().find(Traitement.class, id);
            if (traitement != null)
                return traitement;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Traitement deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Traitement> saveAll(Traitement... traitements) {
        List<Traitement> traitements1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(traitements).forEach(traitement -> {
                persistence.entityManager().persist(traitement);
                traitements1.add(traitement);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return traitements1;
    }

    @Override
    public List<Traitement> deleteMany(Long... ids) {
        List<Traitement> traitements = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Traitement traitement = findById(id);
                if (traitement != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete t From Traitement where t.id");
                    deleteQuery.setParameter("id", id);
                    int deletedTraitement = deleteQuery.getFirstResult();
                    if (deletedTraitement > 0){
                        traitements.add(traitement);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return traitements;
    }

    @Override
    public List<Traitement> deleteMany(Traitement... traitements) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
