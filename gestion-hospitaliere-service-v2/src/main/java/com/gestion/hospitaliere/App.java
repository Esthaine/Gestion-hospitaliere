package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.dao.impl.UserDaoImpl;
import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.model.UserDto;
import com.gestion.hospitaliere.utils.AppUtils;

import java.text.ParseException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, ParseException {

//        Class<User> userClass = (Class<User>) Class.forName("com.gestion.hospitaliere.entity.User");
//        UserDao userDao = new UserDaoImpl(userClass);
//        UserDto userDto = userDao.findUserByName("admin");
//        System.out.println(userDto.toString());
        //System.out.println(userDto.getMotDePasse());

        System.out.println(AppUtils.convertStringToDate("2004-01-22").toInstant());

    }
}
