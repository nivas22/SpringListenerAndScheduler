package com.altimetrik.spring.modal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "taskpreference")
public class TaskPreference {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "task")
    @NotNull(message = "*Please provide your task")
    private String task;

    @Column(name = "pickedTime")
    @NotNull(message = "*Please provide your pickedTime")
    private Long pickedTime;

    public TaskPreference() {
    }

    public TaskPreference(String task, Long pickedTime) {
        this.task = task;
        this.pickedTime = pickedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getPickedTime() {
        return pickedTime;
    }

    public void setPickedTime(Long pickedTime) {
        this.pickedTime = pickedTime;
    }
}
