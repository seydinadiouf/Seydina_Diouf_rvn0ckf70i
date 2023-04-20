package com.anywrgroup.schoolmanager.service.impl;

import com.anywrgroup.schoolmanager.dto.TeacherDTO;
import com.anywrgroup.schoolmanager.entity.Teacher;
import com.anywrgroup.schoolmanager.exceptions.ResourceNotFoundException;
import com.anywrgroup.schoolmanager.mapper.TeacherMapper;
import com.anywrgroup.schoolmanager.repository.SchoolClassRepository;
import com.anywrgroup.schoolmanager.repository.TeacherRepository;
import com.anywrgroup.schoolmanager.service.TeacherService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class TeacherServiceImpl implements TeacherService {
    private static final String TEACHER_NOT_FOUND_MESSAGE = "Teacher not found with the id: {0}";
    private static final String SCHOOL_CLASS_NOT_FOUND_MESSAGE = "School class not found with the id: {0}";

    private final SchoolClassRepository schoolClassRepository;

    private  final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(SchoolClassRepository schoolClassRepository, TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        if (schoolClassRepository.existsById(teacherDTO.getSchoolClassId())){
            throw new ResourceNotFoundException(MessageFormat.format(SCHOOL_CLASS_NOT_FOUND_MESSAGE, teacherDTO.getSchoolClassId()));
        }
        Teacher teacher = teacherRepository.save(teacherMapper.toEntity(teacherDTO));
        return teacherMapper.toDto(teacher);
    }

    @Override
    public TeacherDTO getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).map(teacherMapper::toDto).orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(TEACHER_NOT_FOUND_MESSAGE, teacherId)));
    }
}
