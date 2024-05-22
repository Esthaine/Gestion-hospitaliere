package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.model.UserDto;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public interface  UserDao extends JpaRepository<User>{

  UserDto register();
  UserDto authentication();

  UserDto findUserByName(String username);
  List<User> listOfUserPerRole(String roleName);

}
