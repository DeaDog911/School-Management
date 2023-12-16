package com.deadog.datamanagement.services;

import com.deadog.datamanagement.models.StudyClass;
import com.deadog.datamanagement.repositories.StudentsRepository;
import com.deadog.datamanagement.repositories.StudyClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyClassesService {

    private final StudyClassesRepository studyClassesRepository;
    private final StudentsService studentsService;

    public StudyClassesService(StudyClassesRepository studyClassesRepository, StudentsService studentsService) {
        this.studyClassesRepository = studyClassesRepository;
        this.studentsService = studentsService;
    }

    public List<StudyClass> findAll() {
        List<StudyClass> studyClasses =  studyClassesRepository.findAll();
        for (StudyClass studyClass : studyClasses) {
            int studentsCount = studentsService.getAllByStudyClass(studyClass).size();
            studyClass.setStudentsCount(studentsCount);
        }
        return studyClasses;
    }

    public void save(StudyClass studyClass) {
        studyClassesRepository.save(studyClass);
    }

    public StudyClass findByNumber(String number) {
        return studyClassesRepository.findByNumber(number).orElseThrow();

    }

    public StudyClass findById(int id) {
        return studyClassesRepository.findById(id).orElseThrow();
    }

    public List<StudyClass> findAllWithParams(int teacherId, int subjectId) {
        List<StudyClass> studyClasses = studyClassesRepository.findAllWithParams(teacherId, subjectId);
        for (StudyClass studyClass : studyClasses) {
            int studentsCount = studentsService.getAllByStudyClass(studyClass).size();
            studyClass.setStudentsCount(studentsCount);
        }
        return studyClasses;
    }

    public void deleteById(int studyClassId) {
        studyClassesRepository.deleteById(studyClassId);
    }

    public void update(int studyClassId, StudyClass studyClass) {
        studyClass.setId(studyClassId);
        studyClassesRepository.save(studyClass);
    }
}
