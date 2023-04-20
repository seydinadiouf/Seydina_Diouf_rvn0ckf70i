package com.anywrgroup.schoolmanager.repository;

import com.anywrgroup.schoolmanager.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT student FROM Student student "
            + "where (:schoolClassName is null or LOWER(student.schoolClass.schoolClassName) = :schoolClassName) "
            + "and (:teacherName is null or LOWER(student.schoolClass.teacher.teacherName) = :teacherName) ",
            countQuery = "SELECT count(*) FROM Student student "
                    + "where (:schoolClassName is null or LOWER(student.schoolClass.schoolClassName) = :schoolClassName) "
                    + "and (:teacherName is null or LOWER(student.schoolClass.teacher.teacherName) = :teacherName) ")
    Page<Student> findAllByFilter(@Param("schoolClassName") String schoolClassName, @Param("teacherName") String teacherName, Pageable pageable);
}
