package com.anywrgroup.schoolmanager.service;

import com.anywrgroup.schoolmanager.dto.UserDTO;
import com.anywrgroup.schoolmanager.dto.payload.SignInRequest;
import com.anywrgroup.schoolmanager.dto.SignInResponse;
import com.anywrgroup.schoolmanager.exceptions.UnexpectedErrorException;

;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO readUserByUsername(String username);

    SignInResponse signIn(SignInRequest signInRequest) throws UnexpectedErrorException;

}
