package com.deadog.datamanagement.repositories;

import com.deadog.datamanagement.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersRepository extends JpaRepository<Teacher, Integer> {
}
