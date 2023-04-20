package com.anywrgroup.schoolmanager.mapper;

import com.anywrgroup.schoolmanager.dto.StudentDTO;
import com.anywrgroup.schoolmanager.entity.Student;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "schoolClassId", target = "schoolClass.schoolClassId")
    Student toEntity(StudentDTO studentDTO);

    @InheritInverseConfiguration
    StudentDTO toDto(Student student);
}
