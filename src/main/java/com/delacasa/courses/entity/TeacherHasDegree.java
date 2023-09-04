package com.delacasa.courses.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import java.util.Date;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Immutable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherHasDegree {

    @EmbeddedId
    private TeacherHasDegreePk pk;

    @ManyToOne(fetch = EAGER)
    @MapsId("teacherId")
    private Teacher teacher;

    @ManyToOne(fetch = EAGER)
    @MapsId("degreeId")
    private Degree degree;

    @Column
    @Temporal(TemporalType.DATE)
    private Date earningDate;

}
