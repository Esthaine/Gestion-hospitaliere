package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Pays;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PaysDao implements JpaRepository<Pays>{

    private Persistence persistence;

    public PaysDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Pays save(Pays pays) {
        try{

            if (pays != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(pays);
                persistence.entityManager().getTransaction().commit();
                return pays;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Pays> findAll() {
        Query query = persistence.entityManager().createQuery("Select pa From Pays pa");
        return query.getResultList();
    }

    @Override
    public Pays findById(Long id) {
        try{
            Pays pays = persistence.entityManager().find(Pays.class, id);
            if (pays != null)
                return pays;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Pays deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Pays> saveAll(Pays... payss) {
        List<Pays> pays1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(payss).forEach(pays -> {
                persistence.entityManager().persist(pays);
                pays1.add(pays);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return pays1;
    }

    @Override
    public List<Pays> deleteMany(Long... ids) {
        List<Pays> payss = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Pays pays = findById(id);
                if (pays != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete pa From Pays where pa.id");
                    deleteQuery.setParameter("id", id);
                    int deletedPays = deleteQuery.getFirstResult();
                    if (deletedPays > 0){
                        payss.add(pays);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return payss;
    }

    @Override
    public List<Pays> deleteMany(Pays... pays) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
