package com.delacasa.courses.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import java.util.Date;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Immutable
@Getter
public class StudentHasDegree {

    @EmbeddedId
    private StudentHasDegreePk pk;

    @ManyToOne(fetch = EAGER)
    @MapsId("studentId")
    private Student student;

    @ManyToOne(fetch = EAGER)
    @MapsId("degreeId")
    private Degree degree;

    @Column
    @Temporal(TemporalType.DATE)
    private Date earningDate;
}
