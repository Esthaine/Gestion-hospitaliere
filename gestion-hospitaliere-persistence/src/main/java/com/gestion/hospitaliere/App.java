package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.*;
import com.gestion.hospitaliere.entity.*;
import com.gestion.hospitaliere.model.UserDto;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException {
        //registerAdmin();
        //findUserByName();
    }

    public static void findUserByName() throws ClassNotFoundException {

        Class<User> userClass = (Class<User>) Class.forName("com.gestion.hospitaliere.entity.User");
        UserDao userDao = new UserDaoImpl(userClass);

//        System.out.println("Username: -->" + userDao.findUserByName("admin").getUsername());
        //System.out.println("Password-->" + userDao.findUserByName("admin").getMotDePasse());

//        UserDto userDto = userDao.findUserByName("admin");

        //System.out.println(userDto);
        System.out.println();
//        System.out.println("Username: " + userDto.getUsername());
//        System.out.println("Password: " + userDto.getMotDePasse());

        System.out.println(userDao.findUserByName("john"));
    }
}
