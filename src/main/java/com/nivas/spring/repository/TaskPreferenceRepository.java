package com.nivas.spring.repository;

import com.nivas.spring.modal.TaskPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskPreferenceRepository extends JpaRepository<TaskPreference, Long> {

    @Query("select p from TaskPreference p where p.task = :task")
    TaskPreference findByTask(@Param("task") String task);

    @Modifying
    @Query("UPDATE TaskPreference c SET c.pickedTime = :pickedTime WHERE c.task = :task")
    void updateTask(@Param("task") String task, @Param("pickedTime") Long pickedTime);
}
