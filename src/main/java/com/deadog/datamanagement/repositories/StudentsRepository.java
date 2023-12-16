package com.deadog.datamanagement.repositories;

import com.deadog.datamanagement.models.Student;
import com.deadog.datamanagement.models.StudyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Integer> {
    List<Student> getAllByStudyClass(StudyClass studyClass);
}
