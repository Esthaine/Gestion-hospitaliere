package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.entity.Privilege;

public interface PrivilegeDao extends JpaRepository<Privilege>{
    Privilege findPrivilegeByName(String privilegeName);
}
