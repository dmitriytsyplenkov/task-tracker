package com.dts.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Task> taskList;
}
