package com.delacasa.courses.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
public class StudentHasDegreePk implements Serializable {

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "degree_id")
    private Integer degreeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentHasDegreePk that = (StudentHasDegreePk) o;

        if (!studentId.equals(that.studentId)) return false;
        return degreeId.equals(that.degreeId);
    }

    @Override
    public int hashCode() {
        int result = studentId.hashCode();
        result = 31 * result + degreeId.hashCode();
        return result;
    }
}
