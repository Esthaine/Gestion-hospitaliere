package com.gestion.hospitaliere.model;

import java.util.List;

public class UserDto {

    private Long userId;
    private String username;
    private String motDePasse;
    private String email;

    private List<RoleDto> roleDto;

    public UserDto() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<RoleDto> getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(List<RoleDto> roleDto) {
        this.roleDto = roleDto;
    }

    @Override
    public String toString() {
        return "{id:" +this.userId+
                ", name:" + this.username +
                ", password: " + this.motDePasse +
                ", role: " + this.roleDto +
                "}";
    }
}