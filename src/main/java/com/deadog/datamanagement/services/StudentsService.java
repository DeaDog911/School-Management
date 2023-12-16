package com.deadog.datamanagement.services;

import com.deadog.datamanagement.models.Grade;
import com.deadog.datamanagement.models.Student;
import com.deadog.datamanagement.models.StudyClass;
import com.deadog.datamanagement.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    public void save(Student student) {
        studentsRepository.save(student);
    }

    public Student findById(int id) {
        return studentsRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id) {
        studentsRepository.deleteById(id);
    }

    public void update(int id, Student student) {
        student.setId(id);
        studentsRepository.save(student);
    }

    public List<Student> getAllByStudyClass(StudyClass studyClass) {
        return studentsRepository.getAllByStudyClass(studyClass);
    }

    public boolean studentIs2 (Student student) {
        boolean isStudent2 = false;
        List<Grade> grades = student.getGrades();
        for (Grade grade : grades) {
            if (grade.getValue() == 2) {
                isStudent2 = true;
                break;
            }
        }
        return isStudent2;
    }

    public boolean studentIs5 (Student student) {
        boolean isStudent5 = true;
        List<Grade> grades = student.getGrades();
        for (Grade grade : grades) {
            if (grade.getValue() != 5) {
                isStudent5 = false;
                break;
            }
        }
        if (grades.isEmpty()) {
            isStudent5 = false;
        }
        return isStudent5;
    }

    public boolean studentIs4 (Student student) {
        if (studentIs2(student) || studentIs5(student)) {
            return false;
        }
        boolean isStudent4 = true;
        List<Grade> grades = student.getGrades();
        for (Grade grade : grades) {
            if (grade.getValue() == 3) {
                isStudent4 = false;
                break;
            }
        }
        if (grades.isEmpty()) {
            isStudent4 = false;
        }
        return isStudent4;
    }

    public int getStudents2Count() {
        List<Student> students = studentsRepository.findAll();
        int count = (int) students.stream().filter(this::studentIs2).count();
        return count;
    }

    public int getStudents4Count() {
        List<Student> students = studentsRepository.findAll();
        int count = (int) students.stream().filter(this::studentIs4).count();
        return count;
    }

    public int getStudents5Count() {
        List<Student> students = studentsRepository.findAll();
        int count = (int) students.stream().filter(this::studentIs5).count();
        return count;
    }
}
