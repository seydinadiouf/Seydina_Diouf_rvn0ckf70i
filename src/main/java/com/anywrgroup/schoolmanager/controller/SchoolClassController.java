package com.anywrgroup.schoolmanager.controller;

import com.anywrgroup.schoolmanager.dto.SchoolClassDTO;
import com.anywrgroup.schoolmanager.service.SchoolClassService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/classes")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @ApiOperation(value = "Create a schoolClass")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @PostMapping
    public ResponseEntity<SchoolClassDTO> createSchoolClass(@RequestBody @Valid SchoolClassDTO schoolClassDTO) {
        SchoolClassDTO createdSchoolClass = schoolClassService.createSchoolClass(schoolClassDTO);
        return new ResponseEntity<>(createdSchoolClass, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get a schoolClass by his id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(code = 404, message = "Resource access does not exist"),
            @ApiResponse(code = 500, message = "Internal server error during request processing")})
    @GetMapping("/{schoolClassId}")
    public ResponseEntity<SchoolClassDTO> getSchoolClassById(@PathVariable Long schoolClassId) {
        SchoolClassDTO schoolClassDTO = schoolClassService.getSchoolClassById(schoolClassId);
        return new ResponseEntity<>(schoolClassDTO, HttpStatus.OK);
    }
}
