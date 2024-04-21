package com.gestion.hospitaliere.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RolePrivilege extends AbstractEntity{

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    private Privilege privilege;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
