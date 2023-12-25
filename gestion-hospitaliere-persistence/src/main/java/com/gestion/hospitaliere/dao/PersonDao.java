package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.Person;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PersonDao implements JpaRepository<Person>{

    private Persistence persistence;

    public PersonDao(Persistence persistence) {
        this.persistence = persistence;
    }
    @Override
    public Person save(Person person) {
        try{

            if (person != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(person);
                persistence.entityManager().getTransaction().commit();
                return person;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
        Query query = persistence.entityManager().createQuery("Select per From Person per");
        return query.getResultList();
    }

    @Override
    public Person findById(Long id) {
        try{
            Person person = persistence.entityManager().find(Person.class, id);
            if (person != null)
                return person;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Person deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Person> saveAll(Person... persons) {
        List<Person> persons1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(persons).forEach(person -> {
                persistence.entityManager().persist(person);
                persons1.add(person);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return persons1;
    }

    @Override
    public List<Person> deleteMany(Long... ids) {
        List<Person> persons = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                Person person = findById(id);
                if (person != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete per From Person where per.id");
                    deleteQuery.setParameter("id", id);
                    int deletedPerson = deleteQuery.getFirstResult();
                    if (deletedPerson > 0){
                        persons.add(person);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return persons;
    }

    @Override
    public List<Person> deleteMany(Person... people) {
        try{
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
