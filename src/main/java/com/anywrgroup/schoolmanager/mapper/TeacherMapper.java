package com.anywrgroup.schoolmanager.mapper;

import com.anywrgroup.schoolmanager.dto.TeacherDTO;
import com.anywrgroup.schoolmanager.entity.Teacher;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TeacherMapper {

    @Mapping(source = "schoolClassId", target = "schoolClass.schoolClassId")
    Teacher toEntity(TeacherDTO teacherDTO);

    @InheritInverseConfiguration
    TeacherDTO toDto(Teacher teacher);
}
