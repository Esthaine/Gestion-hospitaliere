package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.entity.Role;

import java.util.List;

public interface RoleDao extends JpaRepository<Role> {

    Role findRoleByName(String roleName);
    List<Role> findRoleByUserNameDifferentDe(String userName);
}
