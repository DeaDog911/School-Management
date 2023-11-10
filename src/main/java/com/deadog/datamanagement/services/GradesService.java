package com.deadog.datamanagement.services;

import com.deadog.datamanagement.models.Grade;
import com.deadog.datamanagement.repositories.GradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GradesService {
    private final GradesRepository gradesRepository;

    public GradesService(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    public void create(Grade grade) {
        gradesRepository.save(grade);
    }

    public Grade findById(int id) {
        return gradesRepository.findById(id).orElseThrow();
    }

    public void update(int grade_id, Grade grade) {
        Optional<Grade> gradeObj = gradesRepository.findById(grade_id);
        if (gradeObj.isPresent()) {
            Grade gradeToUpdate = gradeObj.get();
            gradeToUpdate.setValue(grade.getValue());
            gradesRepository.save(gradeToUpdate);
        }else {
            throw new NoSuchElementException();
        }
    }
}
