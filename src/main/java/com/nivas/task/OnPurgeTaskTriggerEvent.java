package com.nivas.task;

import com.nivas.modal.TaskPreference;
import org.springframework.context.ApplicationEvent;

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
