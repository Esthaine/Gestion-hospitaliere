package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.AntecedentMedical;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AntecedentMedicalDao implements JpaRepository<AntecedentMedical>{

    private Persistence persistence;

    public AntecedentMedicalDao(Persistence persistence) {
        this.persistence = persistence;
    }


    @Override
    public AntecedentMedical save(AntecedentMedical antecedentMedical) {
        try{

            if (antecedentMedical != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(antecedentMedical);
                persistence.entityManager().getTransaction().commit();
                return antecedentMedical;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }


    @Override
    public List<AntecedentMedical> findAll() {
        Query query = persistence.entityManager().createQuery("Select a From AntecedentMedical a");
        return query.getResultList();
    }

    @Override
    public AntecedentMedical findById(Long id) {
        try{
            AntecedentMedical antecedentMedical = persistence.entityManager().find(AntecedentMedical.class, id);
            if (antecedentMedical != null)
                return antecedentMedical;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public AntecedentMedical deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<AntecedentMedical> saveAll(AntecedentMedical... antecedentMedicals) {
        List<AntecedentMedical> antecedentMedicals1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(antecedentMedicals).forEach(antecedentMedical -> {
                persistence.entityManager().persist(antecedentMedical);
                antecedentMedicals1.add(antecedentMedical);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return antecedentMedicals1;
    }

    @Override
    public List<AntecedentMedical> deleteMany(Long... ids) {
        List<AntecedentMedical> antecedentMedicals = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                AntecedentMedical antecedentMedical = findById(id);
                if (antecedentMedical != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete a From AntecedentMedical where a.id");
                    deleteQuery.setParameter("id", id);
                    int deletedAntecedentMedical = deleteQuery.getFirstResult();
                    if (deletedAntecedentMedical > 0){
                        antecedentMedicals.add(antecedentMedical);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return antecedentMedicals;

    }

    @Override
    public List<AntecedentMedical> deleteMany(AntecedentMedical... antecedentMedicals) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }
}
