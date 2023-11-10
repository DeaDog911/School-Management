package com.deadog.datamanagement.services;

import com.deadog.datamanagement.models.Lesson;
import com.deadog.datamanagement.repositories.LessonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonsService {
    private final LessonsRepository lessonsRepository;

    public LessonsService(LessonsRepository lessonsRepository) {
        this.lessonsRepository = lessonsRepository;
    }

    public List<Lesson> findAll() {
        return lessonsRepository.findAll();
    }
}
