package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.ResultatsExamensDao;
import com.gestion.hospitaliere.dao.impl.ResultatExamenDaoImpl;
import com.gestion.hospitaliere.entity.ResultatsExamens;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultatExamensTests {

    ResultatsExamensDao resultatsExamensDao;

    @BeforeEach
    void setUp() throws ClassNotFoundException {
        resultatsExamensDao = new ResultatExamenDaoImpl((Class<ResultatsExamens>) Class.forName("com.gestion.hospitaliere.entity.ResultatsExamens"));
    }


    @Test
    void testFindResultByFiche(){
        List<ResultatsExamens> resultatsExamensList = resultatsExamensDao.findFicheById(1L);
        assertEquals(0, resultatsExamensList.size());
    }
}
