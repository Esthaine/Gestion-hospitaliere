package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Departement;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DepartementDao implements JpaRepository<Departement>{

    private Persistence persistence;

    public DepartementDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Departement save(Departement departement) {
        try{

            if (departement != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(departement);
                persistence.entityManager().getTransaction().commit();
                return departement;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Departement> findAll() {
        Query query = persistence.entityManager().createQuery("Select de From User de");
        return query.getResultList();
    }

    @Override
    public Departement findById(Long id) {
        try{
            Departement departement = persistence.entityManager().find(Departement.class, id);
            if (departement != null)
                return departement;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Departement deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;

    }

    @Override
    public List<Departement> saveAll(Departement... departements) {
        List<Departement> departements1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(departements).forEach(departement -> {
                persistence.entityManager().persist(departement);
                departements1.add(departement);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return departements1;

    }

    @Override
    public List<Departement> deleteMany(Long... ids) {
        List<Departement> departements = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Departement departement = findById(id);
                if (departement != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete de From Departement where de.id");
                    deleteQuery.setParameter("id", id);
                    int deletedDepartement = deleteQuery.getFirstResult();
                    if (deletedDepartement > 0){
                        departements.add(departement);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return departements;
    }

    @Override
    public List<Departement> deleteMany(Departement... departements) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
