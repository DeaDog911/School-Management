package com.deadog.datamanagement.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name="Lesson")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @Column(name="lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="ordinal_number")
    private int ordinalNumber;

    @Column(name="classroom", nullable = false)
    private int classroom;

    @Column(name="day_of_week", nullable = false)
    private int dayOfWeek;

    @ManyToOne
    @JoinColumn(name="subject")
    private Subject subject;

    @OneToOne
    @JoinColumn(name="class")
    private StudyClass studyClass;

    @ManyToOne
    @JoinColumn(name="teacher")
    private Teacher teacher;
}
