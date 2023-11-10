package com.deadog.datamanagement.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Class")
@Getter
@Setter
@NoArgsConstructor
public class StudyClass {
    @Id
    @Column(name="class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="number")
    private String number;

    @ManyToMany(mappedBy = "studyClasses")
    private List<Teacher> teachers;
}
