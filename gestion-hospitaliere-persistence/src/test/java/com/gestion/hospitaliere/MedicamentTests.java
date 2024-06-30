package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.FicheDao;
import com.gestion.hospitaliere.dao.MedicamentDao;
import com.gestion.hospitaliere.dao.impl.FicheDaoImpl;
import com.gestion.hospitaliere.dao.impl.MedicamentDaoImpl;
import com.gestion.hospitaliere.entity.Fiche;
import com.gestion.hospitaliere.entity.Medicament;
import com.gestion.hospitaliere.entity.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicamentTests {

    MedicamentDao medicamentDao;
    FicheDao ficheDao;
    Class<Medicament> medicament;
    Class<Fiche> fiche;
    List<Medicament> medicaments = new ArrayList<>();


    @BeforeEach
    void setUp() throws ClassNotFoundException {
        medicament = (Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament");
        medicamentDao = new MedicamentDaoImpl(medicament);
        fiche = (Class<Fiche>) Class.forName("com.gestion.hospitaliere.entity.Fiche");
        ficheDao = new FicheDaoImpl(fiche);
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
        med2.setDescription("description Tetracycline");
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
        med4.setDescription("description dipirone");
        med4.setPrixUnitaire(120);
        med4.setDatePeremption(LocalDate.now());
        med4.setStock(Stock.IN);

        Medicament med5 = new Medicament();
        med5.setNom("Efferalgan");
        med5.setPosologie("posoligie");
        med5.setDescription("description efferalgan");
        med5.setPrixUnitaire(160);
        med5.setDatePeremption(LocalDate.now());
        med5.setStock(Stock.IN);

        Medicament med6 = new Medicament();
        med6.setNom("Paracetamol");
        med6.setPosologie("posoligie");
        med6.setDescription("description paracetamol");
        med6.setPrixUnitaire(180);
        med6.setDatePeremption(LocalDate.now());
        med6.setStock(Stock.IN);

        medicaments.add(med1);
        medicaments.add(med2);
        medicaments.add(med3);
        medicaments.add(med4);
        medicaments.add(med5);
        medicaments.add(med6);


        medicamentDao.saveAll(medicaments.toArray(new Medicament[medicaments.size()]));

    }

    @Test
    void getAllMedicament(){
        medicamentDao.listMedicamentPerFiche(1L).forEach(System.out::println);
    }

    @Test
    void saveMedicamentPerFiche(){

        Fiche fiche1 = ficheDao.findById(1L);

        medicamentDao.findAll().forEach(med -> {
            med.setFiche(fiche1);
            //medicaments.add(med);
            medicamentDao.save(med);
        });

    }
}
