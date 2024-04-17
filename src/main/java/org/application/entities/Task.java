package org.application.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.application.enums.Priority;
import org.application.enums.Status;
import org.application.enums.TaskType;

import java.time.LocalDate;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String title;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    @ManyToOne(cascade = {CascadeType.MERGE
            , CascadeType.PERSIST
            , CascadeType.DETACH
            , CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

    @ManyToOne(cascade = {CascadeType.MERGE
            , CascadeType.PERSIST
            , CascadeType.DETACH
            , CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Task() {
    }


    public Task(String description, LocalDate dueDate, Priority priority, Status status, String title, TaskType type) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.title = title;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", project=" + project +
                ", user=" + user +
                '}';
    }

}
