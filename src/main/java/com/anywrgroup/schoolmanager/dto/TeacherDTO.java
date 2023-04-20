package com.anywrgroup.schoolmanager.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TeacherDTO {

    private Long teacherId;

    @NotBlank
    private String teacherName;

    @NotNull
    private Long schoolClassId;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(Long schoolClassId) {
        this.schoolClassId = schoolClassId;
    }
}
