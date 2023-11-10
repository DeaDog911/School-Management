package com.deadog.datamanagement.repositories;

import com.deadog.datamanagement.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesRepository extends JpaRepository<Grade, Integer> {
}
