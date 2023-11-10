package com.deadog.datamanagement.services;

import com.deadog.datamanagement.models.StudyClass;
import com.deadog.datamanagement.repositories.StudyClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyClassesService {

    private final StudyClassesRepository studyClassesRepository;

    public StudyClassesService(StudyClassesRepository studyClassesRepository) {
        this.studyClassesRepository = studyClassesRepository;
    }

    public List<StudyClass> findAll() {
        return studyClassesRepository.findAll();
    }

    public void save(StudyClass studyClass) {
        studyClassesRepository.save(studyClass);
    }

    public StudyClass findByNumber(String number) {
        return studyClassesRepository.findByNumber(number).orElseThrow();
    }
}
