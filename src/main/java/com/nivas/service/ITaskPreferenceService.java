package com.nivas.service;

import com.nivas.modal.TaskPreference;


public interface ITaskPreferenceService {

    void updateTask(String task, Long pickedTime);

    TaskPreference findByTask(String task);

    void save(TaskPreference taskPreference);
}
