package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Medicament;
import jakarta.persistence.Query;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MedicamentDao implements JpaRepository<Medicament>{

    private Persistence persistence;

    public MedicamentDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Medicament save(Medicament medicament) {
        try{

            if (medicament != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(medicament);
                persistence.entityManager().getTransaction().commit();
                return medicament;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Medicament> findAll() {
        Query query = persistence.entityManager().createQuery("Select m From Medicament m");
        return query.getResultList();
    }

    @Override
    public Medicament findById(Long id) {
        try{
            Medicament medicament = persistence.entityManager().find(Medicament.class, id);
            if (medicament != null)
                return medicament;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Medicament deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Medicament> saveAll(Medicament... medicaments) {
        List<Medicament> medicaments1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(medicaments).forEach(user -> {
                persistence.entityManager().persist(user);
                medicaments1.add(user);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return medicaments1;
    }

    @Override
    public List<Medicament> deleteMany(Long... ids) {
        List<Medicament> medicaments = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Medicament medicament = findById(id);
                if (medicament != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete m From Medicament where m.id");
                    deleteQuery.setParameter("id", id);
                    int deletedMedicament = deleteQuery.getFirstResult();
                    if (deletedMedicament > 0){
                        medicaments.add(medicament);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return medicaments;
    }

    @Override
    public List<Medicament> deleteMany(Medicament... medicaments) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
