package com.anywrgroup.schoolmanager.service.impl;

import com.anywrgroup.schoolmanager.dto.TeacherDTO;
import com.anywrgroup.schoolmanager.entity.Teacher;
import com.anywrgroup.schoolmanager.exceptions.ResourceNotFoundException;
import com.anywrgroup.schoolmanager.mapper.TeacherMapper;
import com.anywrgroup.schoolmanager.repository.TeacherRepository;
import com.anywrgroup.schoolmanager.service.TeacherService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class TeacherServiceImpl implements TeacherService {
    private static final String TEACHER_NOT_FOUND_MESSAGE = "Teacher not found with the id: {0}";

    private  final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.save(teacherMapper.toEntity(teacherDTO));
        return teacherMapper.toDto(teacher);
    }

    @Override
    public TeacherDTO getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).map(teacherMapper::toDto).orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(TEACHER_NOT_FOUND_MESSAGE, teacherId)));
    }
}
