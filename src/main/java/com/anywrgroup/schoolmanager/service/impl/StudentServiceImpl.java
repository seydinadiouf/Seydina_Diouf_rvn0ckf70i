package com.anywrgroup.schoolmanager.service.impl;

import com.anywrgroup.schoolmanager.dto.SchoolClassDTO;
import com.anywrgroup.schoolmanager.dto.StudentDTO;
import com.anywrgroup.schoolmanager.entity.SchoolClass;
import com.anywrgroup.schoolmanager.entity.Student;
import com.anywrgroup.schoolmanager.exceptions.ResourceNotFoundException;
import com.anywrgroup.schoolmanager.mapper.StudentMapper;
import com.anywrgroup.schoolmanager.repository.StudentRepository;
import com.anywrgroup.schoolmanager.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {
    private static final String STUDENT_NOT_FOUND_MESSAGE = "Student not found with the id: {0}";

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student studentSaved = studentRepository.save(studentMapper.toEntity(studentDTO));
        return studentMapper.toDto(studentSaved);
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        return studentRepository.findById(studentId).map(studentMapper::toDto).orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(STUDENT_NOT_FOUND_MESSAGE, studentId)));
    }


    @Override
    public Page<StudentDTO> getStudentByFilters(String schoolClassName, String teacherName, Pageable pageable) {
        return studentRepository.findAllByFilter(StringUtils.lowerCase(schoolClassName), StringUtils.lowerCase(teacherName), pageable).map(studentMapper::toDto);
    }
/*
    @Override
    public SchoolClassDTO addStudentToSchoolClass(Long studentId, Long schoolClassId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(STUDENT_NOT_FOUND_MESSAGE, studentId)));

        SchoolClass schoolClass = schoolClassRepository.findById(schoolClassId).orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(SCHOOL_CLASS_NOT_FOUND_MESSAGE, schoolClassId )));

        *//* Save class *//*
        addStudentToSchoolClass(student, schoolClass);

        *//* Save school class *//*
        SchoolClass schoolClassSaved = schoolClassRepository.save(schoolClass);

        student.setSchoolClass(schoolClassSaved);

        studentRepository.save(student);

        return schoolClassMapper.toDto(schoolClassSaved);
    }

    private void addStudentToSchoolClass(Student student, SchoolClass schoolClass){
        *//*Get current students*//*
        List<Student> currentStudents = schoolClass.getStudents();

        *//* Adding the new student *//*
        currentStudents.add(student);

        *//* Updating school class *//*
        schoolClass.setStudents(currentStudents.stream().distinct().collect(Collectors.toList()));

    }*/

}
