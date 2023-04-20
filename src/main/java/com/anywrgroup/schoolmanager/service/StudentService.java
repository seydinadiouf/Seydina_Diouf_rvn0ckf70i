package com.anywrgroup.schoolmanager.service;


import com.anywrgroup.schoolmanager.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(Long studentId);


    Page<StudentDTO> getStudentByFilters(String schoolClassName, String teacherName,Pageable pageable);
}
