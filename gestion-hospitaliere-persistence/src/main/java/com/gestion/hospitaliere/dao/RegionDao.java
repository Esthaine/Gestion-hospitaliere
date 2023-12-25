package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Region;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RegionDao implements JpaRepository<Region>{

    private Persistence persistence;

    public RegionDao(Persistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public Region save(Region region) {
        try{

            if (region != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(region);
                persistence.entityManager().getTransaction().commit();
                return region;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Region> findAll() {
        Query query = persistence.entityManager().createQuery("Select r From Region r");
        return query.getResultList();
    }

    @Override
    public Region findById(Long id) {
        try{
            Region region = persistence.entityManager().find(Region.class, id);
            if (region != null)
                return region;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Region deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Region> saveAll(Region... regions) {
        List<Region> regions1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(regions).forEach(region -> {
                persistence.entityManager().persist(region);
                regions1.add(region);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return regions1;

    }

    @Override
    public List<Region> deleteMany(Long... ids) {
        List<Region> regions = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Region region = findById(id);
                if (region != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete r From Region where r.id");
                    deleteQuery.setParameter("id", id);
                    int deletedRegion = deleteQuery.getFirstResult();
                    if (deletedRegion > 0){
                        regions.add(region);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return regions;
    }

    @Override
    public List<Region> deleteMany(Region... regions) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
