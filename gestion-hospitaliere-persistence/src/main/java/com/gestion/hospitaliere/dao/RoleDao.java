package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.entity.Role;

public interface RoleDao extends JpaRepository<Role> {

    Role findRoleByName(String roleName);
}
