package com.gestion.hospitaliere.model;

public class PrivilegeDto {

    private Long privilegeId;
    private String privilegeName;
    private String privilegeDescription;

    public PrivilegeDto() {
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeDescription() {
        return privilegeDescription;
    }

    public void setPrivilegeDescription(String privilegeDescription) {
        this.privilegeDescription = privilegeDescription;
    }

    @Override
    public String toString() {
        return "PrivilegeDto{" +
                "privilegeId=" + privilegeId +
                ", privilegeName='" + privilegeName + '\'' +
                ", privilegeDescription='" + privilegeDescription + '\'' +
                '}';
    }
}
