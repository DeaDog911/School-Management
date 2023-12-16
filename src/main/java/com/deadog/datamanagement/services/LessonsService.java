package com.deadog.datamanagement.services;

import com.deadog.datamanagement.models.Lesson;
import com.deadog.datamanagement.models.LessonPK;
import com.deadog.datamanagement.models.StudyClass;
import com.deadog.datamanagement.repositories.LessonsRepository;
import com.deadog.datamanagement.repositories.StudyClassesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonsService {
    private final LessonsRepository lessonsRepository;
    private final StudyClassesService studyClassesService;

    public LessonsService(LessonsRepository lessonsRepository, StudyClassesService studyClassesService) {
        this.lessonsRepository = lessonsRepository;
        this.studyClassesService = studyClassesService;
    }

    public List<Lesson> findAll() {
        return lessonsRepository.findAll();
    }

    public void save(Lesson lesson) {
        lessonsRepository.save(lesson);
    }

    public Lesson findByPk(int classId, int dayOfWeek, int ordinalNumber) {
        LessonPK lessonPK = new LessonPK();
        lessonPK.setStudyClass(studyClassesService.findById(classId));
        lessonPK.setDayOfWeek(dayOfWeek);
        lessonPK.setOrdinalNumber(ordinalNumber);
        return lessonsRepository.findByLessonPK(lessonPK).orElseThrow();
    }

    public void delete(Lesson lesson) {
        lessonsRepository.delete(lesson);
    }

    public void update(int classId, int dayOfWeek, int ordinalNumber, Lesson lesson) {
        if (findByPk(classId, dayOfWeek, ordinalNumber) != null) {
            lessonsRepository.save(lesson);
        }
    }

    public List<Lesson> findAllWithParams(int studyClassId, int dayOfWeek, int ordinalNumber) {
        List<Lesson> lessons = lessonsRepository.findAll();
        if (!lessons.isEmpty()) {
            return lessonsRepository.findAll().stream().filter(x -> (x.getLessonPK().getStudyClass().getId() == studyClassId || studyClassId == -1)
                            && (x.getLessonPK().getDayOfWeek() == dayOfWeek || dayOfWeek == -1)
                            && (x.getLessonPK().getOrdinalNumber() == ordinalNumber || ordinalNumber == -1))
                    .toList();
        } else {
            return null;
        }
    }
}
