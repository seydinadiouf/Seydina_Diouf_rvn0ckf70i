package com.anywrgroup.schoolmanager.controller;

import com.anywrgroup.schoolmanager.dto.TeacherDTO;
import com.anywrgroup.schoolmanager.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ApiOperation(value = "Create a teacher")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        TeacherDTO createdTeacher = teacherService.createTeacher(teacherDTO);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get a teacher by his id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 404, message = "Resource access does not exist"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long teacherId) {
        TeacherDTO teacherDTO = teacherService.getTeacherById(teacherId);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }
}
