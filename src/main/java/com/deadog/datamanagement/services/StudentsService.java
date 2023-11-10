package com.deadog.datamanagement.services;

import com.deadog.datamanagement.models.Student;
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
}
