package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.AntecedentMedicalDao;
import com.gestion.hospitaliere.dao.impl.AntecdentMedicalDaoImpl;
import com.gestion.hospitaliere.entity.AntecedentMedical;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AntecedentMedicalTest {

    AntecedentMedicalDao antecedentMedicalDao;

    @BeforeEach
    void setUp() throws ClassNotFoundException {
        antecedentMedicalDao = new AntecdentMedicalDaoImpl((Class<AntecedentMedical>) Class.forName("com.gestion.hospitaliere.entity.AntecedentMedical"));
    }


    @Test
    void trouverAntecedentMedical(){
        List<AntecedentMedical> antecedentMedicalList = antecedentMedicalDao.antecedentMedicalFindByFiche(2L);
        assertNotNull(antecedentMedicalList);
    }
}
