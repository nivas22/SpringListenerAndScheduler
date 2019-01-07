package com.nivas.spring.service;

import com.nivas.spring.modal.TaskPreference;
import com.nivas.spring.repository.TaskPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskPreferenceService implements ITaskPreferenceService {

    @Autowired
    private TaskPreferenceRepository repository;

    @Override
    @Transactional
    public void updateTask(String task, Long pickedTime) {
        repository.updateTask(task, pickedTime);
    }


    @Override
    public TaskPreference findByTask(String task) {
        return repository.findByTask(task);
    }

    @Override
    public void save(TaskPreference taskPreference) {
        repository.save(taskPreference);
    }
}
