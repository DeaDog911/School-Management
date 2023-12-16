package com.deadog.datamanagement.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="Lesson")
@Getter
@Setter
@NoArgsConstructor
public class Lesson implements Serializable {
    @EmbeddedId
    private LessonPK lessonPK;

    @Column(name="classroom", nullable = false)
    private int classroom;

    @ManyToOne
    @JoinColumn(name="subject")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name="teacher")
    private Teacher teacher;
}
