package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Docteur;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

public class DocteurDao implements JpaRepository<Docteur>{


    private Persistence persistence;

    public DocteurDao(Persistence persistence) {
        this.persistence = persistence;

    }
        @Override
    public Docteur save(Docteur docteur) {
            try{

                if (docteur != null){
                    persistence.entityManager().getTransaction().begin();
                    persistence.entityManager().persist(docteur);
                    persistence.entityManager().getTransaction().commit();
                    return docteur;
                }
            }catch (Exception e){
                System.out.printf("Message : " + e.getMessage());
            }
            return null;
        }

    @Override
    public List<Docteur> findAll() {
        Query query = persistence.entityManager().createQuery("Select doc From Docteur doc");
        return query.getResultList();
    }

    @Override
    public Docteur findById(Long id) {
        try{
            Docteur docteur = persistence.entityManager().find(Docteur.class, id);
            if (docteur != null)
                return docteur;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Docteur deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Docteur> saveAll(Docteur... docteurs) {
        List<Docteur> docteurs1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(docteurs).forEach(docteur -> {
                persistence.entityManager().persist(docteur);
                docteurs1.add(docteur);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return docteurs1;
    }

    @Override
    public List<Docteur> deleteMany(Long... ids) {
        List<Docteur> docteurs = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Docteur docteur = findById(id);
                if (docteur != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete doc From Docteur where doc.id");
                    deleteQuery.setParameter("id", id);
                    int deletedDocteur = deleteQuery.getFirstResult();
                    if (deletedDocteur > 0){
                        docteurs.add(docteur);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return docteurs;

    }

    @Override
    public List<Docteur> deleteMany(Docteur... docteurs) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }
}
