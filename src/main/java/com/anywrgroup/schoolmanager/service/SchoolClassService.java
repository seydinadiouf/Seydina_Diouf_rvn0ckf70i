package com.anywrgroup.schoolmanager.service;

import com.anywrgroup.schoolmanager.dto.SchoolClassDTO;

public interface SchoolClassService {

    SchoolClassDTO createSchoolClass(SchoolClassDTO schoolClassDTO);

    SchoolClassDTO getSchoolClassById(Long schoolClassId);

}
