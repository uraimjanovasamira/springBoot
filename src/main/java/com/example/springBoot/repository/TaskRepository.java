package com.example.springBoot.repository;

import com.example.springBoot.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT COUNT(distinct t) FROM Task t " +
            "JOIN t.instructor i " +
            "WHERE i.id = :instructorId")
    Long countTasksByInstructor(@Param("instructorId") Long instructorId);
}
