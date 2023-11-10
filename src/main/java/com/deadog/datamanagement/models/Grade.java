package com.deadog.datamanagement.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name = "Grade")
@Getter
@Setter
@NoArgsConstructor
public class Grade {
    @Id
    @Column(name="grade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="value")
    private int value;

    @ManyToOne
    @JoinColumn(name="subject")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name="student")
    private Student student;
}
