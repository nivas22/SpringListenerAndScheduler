package com.altimetrik.spring.service;

import com.altimetrik.spring.modal.TaskPreference;


public interface ITaskPreferenceService {

    void updateTask(String task, Long picked_time);

    TaskPreference findByTask(String task);

    void save(TaskPreference taskPreference);
}
