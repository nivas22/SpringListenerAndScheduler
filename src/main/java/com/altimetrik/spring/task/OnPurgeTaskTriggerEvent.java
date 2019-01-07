package com.altimetrik.spring.task;

import com.altimetrik.spring.modal.TaskPreference;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

public class OnPurgeTaskTriggerEvent extends ApplicationEvent {

    private final Long triggeredDate;
    private final TaskPreference task;

    public OnPurgeTaskTriggerEvent(TaskPreference taskPreference, Long triggeredDate) {
        super(triggeredDate);
        this.triggeredDate = triggeredDate;
        this.task = taskPreference;
    }

    public Long getTriggeredDate() {
        return triggeredDate;
    }

    public TaskPreference getTask() {
        return task;
    }
}
