package com.gestion.hospitaliere.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface IUserService {

    void authentication(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void patientRegistration(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void OrganisationRegistration(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void OrganizationMemberList(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void userProfile(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void userOrganisationProfileUpdate(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void updateUserProfile(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;

}
