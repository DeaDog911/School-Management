package com.deadog.datamanagement.services;

import com.deadog.datamanagement.models.Student;
import com.deadog.datamanagement.models.Subject;
import com.deadog.datamanagement.repositories.StudentsRepository;
import com.deadog.datamanagement.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsService {
    private final SubjectsRepository subjectsRepository;

    public SubjectsService(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }

    public List<Subject> findAll() {
        return subjectsRepository.findAll();
    }


    public void save(Subject subject) {
        subjectsRepository.save(subject);
    }

    public Subject findByName(String subjectName) {
        return subjectsRepository.findByName(subjectName).orElseThrow();
    }
}
