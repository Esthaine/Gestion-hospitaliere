package com.gestion.hospitaliere.service;

import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.model.request.UserRequest;


public interface IAuthentificationService {
    User authentification(UserRequest userRequest);
}
