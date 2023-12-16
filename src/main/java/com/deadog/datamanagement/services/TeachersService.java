package com.deadog.datamanagement.services;

import com.deadog.datamanagement.models.StudyClass;
import com.deadog.datamanagement.models.Subject;
import com.deadog.datamanagement.models.Teacher;
import com.deadog.datamanagement.repositories.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.List;

@Service
public class TeachersService {
    private final TeachersRepository teachersRepository;
    private final SubjectsService subjectsService;
    private final StudyClassesService studyClassesService;

    public TeachersService(TeachersRepository teachersRepository, SubjectsService subjectsService, SubjectsService subjectsService1, StudyClassesService studyClassesService) {
        this.teachersRepository = teachersRepository;
        this.subjectsService = subjectsService1;
        this.studyClassesService = studyClassesService;
    }

    public List<Teacher> findAll() {
        return teachersRepository.findAll();
    }

    public void save(Teacher teacher) {
        teachersRepository.save(teacher);
    }

    public Teacher create(String name, int classroom, List<String> subjectsNames, List<String> studyClassesNumbers) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setClassroom(classroom);
        List<Subject> subjects = new LinkedList<>();
        List<StudyClass> studyClasses = new LinkedList<>();
        for (String subjectName : subjectsNames) {
            Subject subject = subjectsService.findByName(subjectName);
            subjects.add(subject);
        }
        for (String studyClassNumber : studyClassesNumbers) {
            StudyClass studyClass = studyClassesService.findByNumber(studyClassNumber);
            studyClasses.add(studyClass);
        }
        System.out.println(subjects);
        System.out.println(studyClasses);
        teacher.setSubjects(subjects);
        teacher.setStudyClasses(studyClasses);
        return teacher;
    }

    public void delete(Teacher teacher) {
        teachersRepository.delete(teacher);
    }

    public Teacher findById(int id) {
        return teachersRepository.findById(id).orElseThrow();
    }

    public void update(int id, Teacher teacher) {
        teacher.setId(id);
        teachersRepository.save(teacher);
    }

    public int getRoomsCount() {
        return teachersRepository.getRoomsCount();
    }
}
