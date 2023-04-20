package com.anywrgroup.schoolmanager.service.impl;

import com.anywrgroup.schoolmanager.dto.SchoolClassDTO;
import com.anywrgroup.schoolmanager.entity.SchoolClass;
import com.anywrgroup.schoolmanager.exceptions.ResourceAlreadyExistException;
import com.anywrgroup.schoolmanager.exceptions.ResourceNotFoundException;
import com.anywrgroup.schoolmanager.mapper.SchoolClassMapper;
import com.anywrgroup.schoolmanager.repository.SchoolClassRepository;
import com.anywrgroup.schoolmanager.service.SchoolClassService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service
public class SchoolClassServiceImpl implements SchoolClassService {
    private static final String SCHOOL_CLASS_NOT_FOUND_MESSAGE = "School class not found with the id: {0}";
    private static final String SCHOOL_CLASS_ALREADY_EXITS_MESSAGE = "School class with the name already exist: {0}";

    private final SchoolClassRepository schoolClassRepository;

    private final SchoolClassMapper schoolClassMapper;


    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository, SchoolClassMapper schoolClassMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassMapper = schoolClassMapper;
    }

    @Override
    public SchoolClassDTO createSchoolClass(SchoolClassDTO schoolClassDTO) {
        if (schoolClassRepository.existsBySchoolClassName(schoolClassDTO.getSchoolClassName())){
            throw new ResourceAlreadyExistException(MessageFormat.format(SCHOOL_CLASS_ALREADY_EXITS_MESSAGE, schoolClassDTO.getSchoolClassName()));
        }
        SchoolClass schoolClassSaved = schoolClassRepository.save(schoolClassMapper.toEntity(schoolClassDTO));
        return schoolClassMapper.toDto(schoolClassSaved);
    }

    @Override
    public SchoolClassDTO getSchoolClassById(Long schoolClassId) {
        return schoolClassRepository.findById(schoolClassId).map(schoolClassMapper::toDto).orElseThrow(()-> new ResourceNotFoundException(MessageFormat.format(SCHOOL_CLASS_NOT_FOUND_MESSAGE, schoolClassId)));
    }


}
