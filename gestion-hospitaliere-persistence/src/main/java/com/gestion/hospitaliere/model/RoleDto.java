package com.gestion.hospitaliere.model;

import java.util.List;

public class RoleDto {

    private Long roleId;
    private String roleName;
    private String roleDescription;
    private List<PrivilegeDto> privilegeDto;

    public RoleDto() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public List<PrivilegeDto> getPrivilegeDto() {
        return privilegeDto;
    }

    public void setPrivilegeDto(List<PrivilegeDto> privilegeDto) {
        this.privilegeDto = privilegeDto;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", privilegeDto=" + privilegeDto +
                '}';
    }
}
