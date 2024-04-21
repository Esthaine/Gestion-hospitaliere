package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.RolePrivilegeDao;
import com.gestion.hospitaliere.entity.RolePrivilege;

public class RolePrivilegeDaoImpl extends JpaRepositoryImpl<RolePrivilege> implements RolePrivilegeDao {
    public RolePrivilegeDaoImpl(Class<RolePrivilege> clazz) {
        super(clazz);
    }
}
