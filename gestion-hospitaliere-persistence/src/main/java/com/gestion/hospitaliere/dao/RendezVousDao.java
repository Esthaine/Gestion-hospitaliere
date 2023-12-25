package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.RendezVous;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RendezVousDao implements JpaRepository<RendezVous>{

    private Persistence persistence;

    public RendezVousDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public RendezVous save(RendezVous rendezVous) {
        try{

            if (rendezVous != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(rendezVous);
                persistence.entityManager().getTransaction().commit();
                return rendezVous;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<RendezVous> findAll() {
        Query query = persistence.entityManager().createQuery("Select ren From RendezVous ren");
        return query.getResultList();
    }

    @Override
    public RendezVous findById(Long id) {
        try{
            RendezVous rendezVous = persistence.entityManager().find(RendezVous.class, id);
            if (rendezVous != null)
                return rendezVous;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public RendezVous deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<RendezVous> saveAll(RendezVous... rendezVouss) {
        List<RendezVous> rendezVous1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(rendezVouss).forEach(rendezVous -> {
                persistence.entityManager().persist(rendezVous);
                rendezVous1.add(rendezVous);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return rendezVous1;
    }

    @Override
    public List<RendezVous> deleteMany(Long... ids) {
        List<RendezVous> rendezVouss = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                RendezVous rendezVous = findById(id);
                if (rendezVous != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete ren From RendezVous where ren.id");
                    deleteQuery.setParameter("id", id);
                    int deletedRendezVous = deleteQuery.getFirstResult();
                    if (deletedRendezVous > 0){
                        rendezVouss.add(rendezVous);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return rendezVouss;
    }

    @Override
    public List<RendezVous> deleteMany(RendezVous... rendezVous) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
