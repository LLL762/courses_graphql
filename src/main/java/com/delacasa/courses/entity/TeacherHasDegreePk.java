package com.delacasa.courses.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
public class TeacherHasDegreePk implements Serializable {

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "degree_id")
    private Integer degreeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherHasDegreePk that = (TeacherHasDegreePk) o;

        if (!teacherId.equals(that.teacherId)) return false;
        return degreeId.equals(that.degreeId);
    }

    @Override
    public int hashCode() {
        int result = teacherId.hashCode();
        result = 31 * result + degreeId.hashCode();
        return result;
    }
}
