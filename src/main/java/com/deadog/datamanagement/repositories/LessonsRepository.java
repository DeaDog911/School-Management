package com.deadog.datamanagement.repositories;

import com.deadog.datamanagement.models.Lesson;
import com.deadog.datamanagement.models.LessonPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonsRepository extends JpaRepository<Lesson, Integer> {
    Optional<Lesson> findByLessonPK(LessonPK lessonPK);
}
