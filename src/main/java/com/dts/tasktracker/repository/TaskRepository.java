package com.dts.tasktracker.repository;

import com.dts.tasktracker.model.Project;
import com.dts.tasktracker.model.Task;
import com.dts.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProjectInAndUser(List<Project> projects, User user);

    List<Task> findAllByProject(Project project);
}
