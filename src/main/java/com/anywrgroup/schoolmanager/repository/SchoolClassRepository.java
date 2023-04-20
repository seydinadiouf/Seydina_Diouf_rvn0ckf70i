package com.anywrgroup.schoolmanager.repository;

import com.anywrgroup.schoolmanager.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    boolean existsBySchoolClassName(String schoolClassName);

}
