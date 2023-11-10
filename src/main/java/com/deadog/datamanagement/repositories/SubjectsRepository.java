package com.deadog.datamanagement.repositories;

import com.deadog.datamanagement.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectsRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findByName(String name);
}
