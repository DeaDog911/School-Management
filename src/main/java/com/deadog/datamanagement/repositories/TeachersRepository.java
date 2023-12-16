package com.deadog.datamanagement.repositories;

import com.deadog.datamanagement.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeachersRepository extends JpaRepository<Teacher, Integer> {
    @Query("""
        select count(t) from Teacher t where t.classroom is not null
    """)
    int getRoomsCount();
}
