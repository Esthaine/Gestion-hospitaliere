package com.gestion.hospitaliere.utils;

import java.util.Random;

public class PersistenceUtils {

    public static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public static String departmentNumberGeneration(){
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%04d", number);
    }
}
