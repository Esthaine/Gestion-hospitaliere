package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.model.UserDto;

import java.util.List;

public interface  UserDao extends JpaRepository<User>{

  UserDto register();
  UserDto authentication();

  UserDto findUserByName(String username);
  List<User> listOfUserPerRole(String roleName);
  List<User> listOfUserByDepartment(Long id);

}
