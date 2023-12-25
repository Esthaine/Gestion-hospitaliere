package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Rendez_vous;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Rendez_vousDao implements JpaRepository<Rendez_vous>{

    private Persistence persistence;

    public Rendez_vousDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Rendez_vous save(Rendez_vous rendez_vous) {
        try{

            if (rendez_vous != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(rendez_vous);
                persistence.entityManager().getTransaction().commit();
                return rendez_vous;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Rendez_vous> findAll() {
        Query query = persistence.entityManager().createQuery("Select ren From Rendez_vous ren");
        return query.getResultList();
    }

    @Override
    public Rendez_vous findById(Long id) {
        try{
            Rendez_vous rendez_vous = persistence.entityManager().find(Rendez_vous.class, id);
            if (rendez_vous != null)
                return rendez_vous;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Rendez_vous deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Rendez_vous> saveAll(Rendez_vous... rendez_vouss) {
        List<Rendez_vous> rendez_vous1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(rendez_vouss).forEach(rendez_vous -> {
                persistence.entityManager().persist(rendez_vous);
                rendez_vous1.add(rendez_vous);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return rendez_vous1;
    }

    @Override
    public List<Rendez_vous> deleteMany(Long... ids) {
        List<Rendez_vous> rendez_vouss = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Rendez_vous rendez_vous = findById(id);
                if (rendez_vous != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete ren From Rendez_vous where ren.id");
                    deleteQuery.setParameter("id", id);
                    int deletedRendez_vous = deleteQuery.getFirstResult();
                    if (deletedRendez_vous > 0){
                        rendez_vouss.add(rendez_vous);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return rendez_vouss;
    }

    @Override
    public List<Rendez_vous> deleteMany(Rendez_vous... rendez_vous) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
