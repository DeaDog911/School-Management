package com.deadog.datamanagement.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Embeddable
@Data
public class LessonPK implements Serializable {
    @Column(name="ordinal_number")
    private int ordinalNumber;

    @Column(name="day_of_week", nullable = false)
    private int dayOfWeek;

    @OneToOne
    @JoinColumn(name="class")
    private StudyClass studyClass;
}
