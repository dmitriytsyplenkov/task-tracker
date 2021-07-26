package com.dts.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "projects")
public class Project {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Task> taskList;

    @JsonIgnore
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<User> userList;
}
