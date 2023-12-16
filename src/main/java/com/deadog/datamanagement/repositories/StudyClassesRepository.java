package com.deadog.datamanagement.repositories;

import com.deadog.datamanagement.models.Lesson;
import com.deadog.datamanagement.models.StudyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyClassesRepository extends JpaRepository<StudyClass, Integer> {
    Optional<StudyClass> findByNumber(String number);
    @Query("""
        select c from StudyClass c
                 left join c.teachers tc
                 left join tc.subjects ts
                 where (:teacherId = -1 or tc.id = :teacherId) and (:subjectId = -1 or ts.id = :subjectId)
    """)
    List<StudyClass> findAllWithParams(@Param("teacherId") int teacherId, @Param("subjectId") int subjectId);
}
