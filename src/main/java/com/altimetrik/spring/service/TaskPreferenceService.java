package com.altimetrik.spring.service;

import com.altimetrik.spring.modal.TaskPreference;
import com.altimetrik.spring.repository.TaskPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskPreferenceService implements ITaskPreferenceService {

    @Autowired
    private TaskPreferenceRepository repository;

    @Override
    @Transactional
    public void updateTask(String task, Long picked_time) {
        repository.updateTask(task, picked_time);
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
