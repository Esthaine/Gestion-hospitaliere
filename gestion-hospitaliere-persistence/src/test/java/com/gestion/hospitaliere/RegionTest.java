package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.PaysDao;
import com.gestion.hospitaliere.dao.RegionDao;
import com.gestion.hospitaliere.dao.VilleDao;
import com.gestion.hospitaliere.dao.impl.PaysDaoImpl;
import com.gestion.hospitaliere.dao.impl.RegionDaoImpl;
import com.gestion.hospitaliere.dao.impl.VilleDaoImpl;
import com.gestion.hospitaliere.entity.Pays;
import com.gestion.hospitaliere.entity.Region;
import com.gestion.hospitaliere.entity.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class RegionTest {

    Class<Ville> villeClass;
    Class<Region> regionClass ;
    Class<Pays> paysClass;

    VilleDao villeDao;
    RegionDao regionDao;
    PaysDao paysDao;

    @BeforeEach
    void setUp() throws ClassNotFoundException {
        villeClass = (Class<Ville>) Class.forName("com.gestion.hospitaliere.entity.Ville");
        regionClass = (Class<Region>) Class.forName("com.gestion.hospitaliere.entity.Region");
        paysClass = (Class<Pays>) Class.forName("com.gestion.hospitaliere.entity.Pays");

        villeDao = new VilleDaoImpl(villeClass);
        regionDao = new RegionDaoImpl(regionClass);
        paysDao = new PaysDaoImpl(paysClass);
    }


    @Test
    void creerPaysAvecDifferentRegionEtVille(){

        Pays pays = new Pays();
        pays.setNom("Republique democratique du Congo");
        pays.setCodeIso("243");

        Set<Region> regionsSet = new HashSet<>();
        pays.setRegions(regionsSet);

        paysDao.save(pays);

        //---------------------

        Region regionLba = new Region();
        regionLba.setRegionName("Lualaba");
        regionLba.setCode("LBA");
        regionLba.setPays(pays);

        Set<Ville> lbaSet = new HashSet<>();

        regionLba.setVille(lbaSet);

        regionDao.save(regionLba);



        Region regionHtk = new Region();
        regionHtk.setRegionName("Haut-Katanga");
        regionHtk.setCode("03");
        regionHtk.setCode("HTK");
        regionHtk.setPays(pays);


        regionDao.save(regionHtk);

        Region regionKin = new Region();
        regionKin.setRegionName("Kinshasa");
        regionKin.setCode("KIN");
        regionKin.setPays(pays);

        regionDao.save(regionKin);



        //Congo DRC

        Ville villeKin = new Ville();
        villeKin.setCodePostal("KIN01");
        villeKin.setNom("Kinshasa");
        villeKin.setRegion(regionKin);
        villeDao.save(villeKin);


        Ville villeKolwezi = new Ville();
        villeKolwezi.setCodePostal("LBA01");
        villeKolwezi.setNom("Kolwezi");
        villeKolwezi.setRegion(regionLba);

        villeDao.save(villeKolwezi);

        Ville villeLshi = new Ville();
        villeLshi.setNom("Lubumbashi");
        villeLshi.setCodePostal("HTK01");
        villeLshi.setRegion(regionHtk);

        villeDao.save(villeLshi);


        Ville villeLikasi = new Ville();
        villeLikasi.setNom("Likasi");
        villeLikasi.setCodePostal("HTK02");
        villeLikasi.setRegion(regionHtk);

        villeDao.save(villeLikasi);

        Ville villeKipushi = new Ville();
        villeKipushi.setNom("Kipushi");
        villeKipushi.setCodePostal("HTK03");
        villeKipushi.setRegion(regionHtk);

        villeDao.save(villeKipushi);




    }
}
