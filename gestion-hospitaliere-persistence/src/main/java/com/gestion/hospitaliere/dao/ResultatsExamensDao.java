package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.ResultatsExamens;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ResultatsExamensDao implements JpaRepository<ResultatsExamens>{

    private Persistence persistence;

    public ResultatsExamensDao(Persistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public ResultatsExamens save(ResultatsExamens resultatsExamens) {
        try{

            if (resultatsExamens != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(resultatsExamens);
                persistence.entityManager().getTransaction().commit();
                return resultatsExamens;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ResultatsExamens> findAll() {
        Query query = persistence.entityManager().createQuery("Select res From ResultatsExamens res");
        return query.getResultList();
    }

    @Override
    public ResultatsExamens findById(Long id) {
        try{
            ResultatsExamens resultatsExamens = persistence.entityManager().find(ResultatsExamens.class, id);
            if (resultatsExamens != null)
                return resultatsExamens;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public ResultatsExamens deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ResultatsExamens> saveAll(ResultatsExamens... resultatsExamenss) {
        List<ResultatsExamens> resultatsExamens1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(resultatsExamenss).forEach(resultatsExamens -> {
                persistence.entityManager().persist(resultatsExamens);
                resultatsExamens1.add(resultatsExamens);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return resultatsExamens1;
    }

    @Override
    public List<ResultatsExamens> deleteMany(Long... ids) {
        List<ResultatsExamens> resultatsExamenss = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                ResultatsExamens resultatsExamens = findById(id);
                if (resultatsExamens != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete res From ResultatsExamens where u.id");
                    deleteQuery.setParameter("id", id);
                    int deletedResultatsExamens = deleteQuery.getFirstResult();
                    if (deletedResultatsExamens > 0){
                        resultatsExamenss.add(resultatsExamens);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return resultatsExamenss;
    }

    @Override
    public List<ResultatsExamens> deleteMany(ResultatsExamens... resultatsExamens) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
