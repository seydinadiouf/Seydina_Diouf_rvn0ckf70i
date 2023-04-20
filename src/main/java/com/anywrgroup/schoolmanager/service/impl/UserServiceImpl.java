package com.anywrgroup.schoolmanager.service.impl;


import com.anywrgroup.schoolmanager.dto.enumeration.Role;
import com.anywrgroup.schoolmanager.dto.UserDTO;
import com.anywrgroup.schoolmanager.dto.payload.SignInRequest;
import com.anywrgroup.schoolmanager.dto.SignInResponse;
import com.anywrgroup.schoolmanager.entity.User;
import com.anywrgroup.schoolmanager.exceptions.ForbiddenActionException;
import com.anywrgroup.schoolmanager.exceptions.ResourceNotFoundException;
import com.anywrgroup.schoolmanager.exceptions.UnexpectedErrorException;
import com.anywrgroup.schoolmanager.mapper.UserMapper;
import com.anywrgroup.schoolmanager.repository.UserRepository;
import com.anywrgroup.schoolmanager.security.jwt.JwtUtils;
import com.anywrgroup.schoolmanager.security.model.UserDetailsImpl;
import com.anywrgroup.schoolmanager.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ALREADY_EXIST = "User with this email or username already exist";
    private static final String USER_NOT_FOUND = "User with id {0} not found";

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final JwtUtils jwtUtils;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        /* Check if user already exits */
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ForbiddenActionException(USER_ALREADY_EXIST);
        }

        User user = userMapper.toEntity(userDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO readUserByUsername(String username) {
        UserDTO userDTO = userRepository.findByUsername(username).map(userMapper::toDto).orElseThrow(()-> new ResourceNotFoundException(MessageFormat.format(USER_NOT_FOUND, username)));

        /* Set password to null for security purpose (Could be handle in a better way) */
        userDTO.setPassword(null);

        return userDTO;
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) throws UnexpectedErrorException {
        /* Authenticating user and generate token*/
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Retrieving roles (Could be handle in a better way)
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Role role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().map(Role::valueOf).orElseThrow(() -> new UnexpectedErrorException("Unexpected error occurs"));

        return new SignInResponse(signInRequest.getUsername(), jwt, role);

    }


}
