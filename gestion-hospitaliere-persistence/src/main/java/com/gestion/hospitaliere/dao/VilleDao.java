package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Ville;
import jakarta.persistence.Query;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class VilleDao implements JpaRepository<Ville>{

    private Persistence persistence;

    public VilleDao(Persistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public Ville save(Ville ville) {
        try{

            if (ville != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(ville);
                persistence.entityManager().getTransaction().commit();
                return ville;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Ville> findAll() {
        Query query = persistence.entityManager().createQuery("Select v From Ville v");
        return query.getResultList();
    }

    @Override
    public Ville findById(Long id) {
        try{
            Ville ville = persistence.entityManager().find(Ville.class, id);
            if (ville != null)
                return ville;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Ville deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Ville> saveAll(Ville... villes) {
        List<Ville> villes1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(villes).forEach(ville -> {
                persistence.entityManager().persist(ville);
                villes1.add(ville);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return villes1;
    }

    @Override
    public List<Ville> deleteMany(Long... ids) {
        List<Ville> villes = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Ville ville = findById(id);
                if (ville != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete v From Ville where v.id");
                    deleteQuery.setParameter("id", id);
                    int deletedVille = deleteQuery.getFirstResult();
                    if (deletedVille > 0){
                        villes.add(ville);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return villes;
    }

    @Override
    public List<Ville> deleteMany(Ville... villes) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
