package com.anywrgroup.schoolmanager.controller;

import com.anywrgroup.schoolmanager.dto.SignInResponse;
import com.anywrgroup.schoolmanager.dto.UserDTO;
import com.anywrgroup.schoolmanager.dto.payload.SignInRequest;
import com.anywrgroup.schoolmanager.exceptions.UnexpectedErrorException;
import com.anywrgroup.schoolmanager.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) throws UnexpectedErrorException {
        SignInResponse signInResponse = userService.signIn(signInRequest);
        return new ResponseEntity<>(signInResponse, HttpStatus.OK);
    }


    @ApiOperation(value = "Get a user by his username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 404, message = "Resource access does not exist"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        UserDTO userDTO = userService.readUserByUsername(username);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
