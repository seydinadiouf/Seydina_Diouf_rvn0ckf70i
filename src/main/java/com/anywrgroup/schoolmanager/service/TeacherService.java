package com.anywrgroup.schoolmanager.service;

import com.anywrgroup.schoolmanager.dto.TeacherDTO;

public interface TeacherService {

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO getTeacherById(Long teacherId);
}
