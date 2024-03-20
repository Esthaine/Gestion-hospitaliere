package com.gestion.hospitaliere.service;

import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.model.request.UserRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface IAuthentificationService {

    void authentication(HttpServletRequest request , HttpServletResponse response) ;
}
