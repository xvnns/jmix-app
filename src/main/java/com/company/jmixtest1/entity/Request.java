package com.company.jmixtest1.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.UUID;

@JmixEntity
@Table(name = "REQUEST")
@Entity(name = "Request")
@XmlRootElement(name = "request")
public class Request {
    @InstanceName
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @NotBlank
    @Column(name = "INPUT_DATA", nullable = false)
    @Lob
    private String inputData;

    @NotNull
    @Column(name = "TASK_TYPE", nullable = false)
    private String taskType;

    public TaskType getTaskType() {
        return taskType == null ? null : TaskType.fromId(taskType);
    }

    public void setTaskType(TaskType task_type) {
        this.taskType = task_type == null ? null : task_type.getId();
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String input_data) {
        this.inputData = input_data;
    }

    public UUID getId() {
        return id;
    }

    @XmlTransient
    public void setId(UUID id) {
        this.id = id;
    }
}