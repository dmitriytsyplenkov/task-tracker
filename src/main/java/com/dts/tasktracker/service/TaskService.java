package com.dts.tasktracker.service;

import com.dts.tasktracker.model.Project;
import com.dts.tasktracker.model.Task;
import com.dts.tasktracker.model.User;

import java.util.List;

public interface TaskService {
    void delete(Task task);

    Task findById(Long id);

    Task saveTask(Task task);

    Task saveSubTask(Task task);

    void closeTask(Task task);

    void updateParentRemainingTime(Task task, boolean increase);

    void closeTaskAndSubTasks(Task task);

    void assignUserOnTask(Task task, User user);

    List<Task> getUserTasksOnProjects(User user, List<Project> projects);

    List<Task> projectTasks(Project project);
}
