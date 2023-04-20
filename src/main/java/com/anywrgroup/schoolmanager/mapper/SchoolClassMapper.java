package com.anywrgroup.schoolmanager.mapper;

import com.anywrgroup.schoolmanager.dto.SchoolClassDTO;
import com.anywrgroup.schoolmanager.entity.SchoolClass;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {StudentMapper.class, TeacherMapper.class})
public interface SchoolClassMapper {
    SchoolClass toEntity(SchoolClassDTO schoolClassDTO);

    SchoolClassDTO toDto(SchoolClass schoolClass);
}
