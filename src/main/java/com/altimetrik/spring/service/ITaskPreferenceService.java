package com.altimetrik.spring.service;

import com.altimetrik.spring.modal.TaskPreference;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskPreferenceService {

    void updateTask(String task, Long picked_time);

    TaskPreference findByTask(String task);

    void save(TaskPreference taskPreference);
}
