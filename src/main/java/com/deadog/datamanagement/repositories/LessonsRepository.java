package com.deadog.datamanagement.repositories;

import com.deadog.datamanagement.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonsRepository extends JpaRepository<Lesson, Integer> {
}
