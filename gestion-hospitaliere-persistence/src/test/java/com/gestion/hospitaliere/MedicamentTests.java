package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.MedicamentDao;
import com.gestion.hospitaliere.dao.impl.MedicamentDaoImpl;
import com.gestion.hospitaliere.entity.Medicament;
import com.gestion.hospitaliere.entity.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicamentTests {

    MedicamentDao medicamentDao;
    Class<Medicament> medicament;
    List<Medicament> medicaments = new ArrayList<>();

    @BeforeEach
    void setUp() throws ClassNotFoundException {
        medicament = (Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament");
        medicamentDao = new MedicamentDaoImpl(medicament);
    }



    @Test
    void createMedicamentSuite(){


        Medicament med1 = new Medicament();
        med1.setNom("Aspirine");
        med1.setPosologie("posologie");
        med1.setDescription("description aspirine");
        med1.setPrixUnitaire(100);
        med1.setDatePeremption(LocalDate.now());
        med1.setStock(Stock.IN);

        Medicament med2 = new Medicament();
        med2.setNom("Tetracycline");
        med2.setPosologie("posologie");
        med2.setDescription("description");
        med2.setPrixUnitaire(150);
        med2.setDatePeremption(LocalDate.now());
        med2.setStock(Stock.IN);

        Medicament med3 = new Medicament();
        med3.setNom("Chloramphenicole");
        med3.setPosologie("posologie");
        med3.setDescription("description chloramphenicole");
        med3.setPrixUnitaire(200);
        med3.setDatePeremption(LocalDate.now());
        med3.setStock(Stock.IN);


        Medicament med4 = new Medicament();
        med4.setNom("Dipirone");
        med4.setPosologie("posologie");
        med4.setDescription("description");
        med4.setPrixUnitaire(100);
        med4.setDatePeremption(LocalDate.now());
        med4.setStock(Stock.IN);

        medicaments.add(med1);
        medicaments.add(med2);
        medicaments.add(med3);
        medicaments.add(med4);


        medicamentDao.saveAll(medicaments.toArray(new Medicament[medicaments.size()]));

    }
}
