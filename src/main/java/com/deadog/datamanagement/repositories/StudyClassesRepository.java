package com.deadog.datamanagement.repositories;

import com.deadog.datamanagement.models.StudyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyClassesRepository extends JpaRepository<StudyClass, Integer> {
    Optional<StudyClass> findByNumber(String number);
}
