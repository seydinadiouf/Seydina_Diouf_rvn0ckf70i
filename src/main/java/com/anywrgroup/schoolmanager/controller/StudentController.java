package com.anywrgroup.schoolmanager.controller;

import com.anywrgroup.schoolmanager.dto.StudentDTO;
import com.anywrgroup.schoolmanager.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "Create a student")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get a student by his id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 404, message = "Resource access does not exist"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @GetMapping
    public Page<StudentDTO> getStudentByFilter(@RequestParam String schoolClassName, @RequestParam String teacherName, Pageable pageable) {
        return studentService.getStudentByFilters(schoolClassName, teacherName, pageable);
    }

    @ApiOperation(value = "Get students using filter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 404, message = "Resource access does not exist"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId) {
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
}
