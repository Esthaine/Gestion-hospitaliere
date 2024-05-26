package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.PremierSoinDao;
import com.gestion.hospitaliere.dao.impl.PremierSoinDaoImpl;
import com.gestion.hospitaliere.entity.PremierSoin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Permission;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PremierSoinTest {

    PremierSoinDao premierSoinDao;
    @BeforeEach
    void setUp() throws ClassNotFoundException {
        premierSoinDao = new PremierSoinDaoImpl((Class<PremierSoin>) Class.forName("com.gestion.hospitaliere.entity.PremierSoin"));
    }

    @Test
    void testRetrouverPremierSoin(){
       List<PremierSoin> premierSoins =  premierSoinDao.findByFiche(2L);

       assertNotNull(premierSoins);
       System.out.println(premierSoins);
    }
}
