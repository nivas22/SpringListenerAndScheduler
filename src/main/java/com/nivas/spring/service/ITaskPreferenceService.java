package com.nivas.spring.service;

import com.nivas.spring.modal.TaskPreference;


public interface ITaskPreferenceService {

    void updateTask(String task, Long pickedTime);

    TaskPreference findByTask(String task);

    void save(TaskPreference taskPreference);
}
