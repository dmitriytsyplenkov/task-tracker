package com.dts.tasktracker.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Project project;
    @Column(name = "total_remaining_time")
    private long totalRemainingTime; //for example it could be measured in minutes
    @Column(name = "is_closed")
    private boolean isClosed;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;
    @OneToMany(mappedBy = "parentTask",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Task> subTasks;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;
}
