package com.dts.tasktracker.service;

import com.dts.tasktracker.exception.NotFoundException;
import com.dts.tasktracker.model.Project;
import com.dts.tasktracker.model.Task;
import com.dts.tasktracker.model.User;
import com.dts.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskServiceJpaImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceJpaImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

    }

    @Override
    @Transactional
    public void delete(Task task) {
        if (!task.isClosed()) {
            updateParentRemainingTime(task, false);
        }
        taskRepository.delete(task);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(()-> new NotFoundException("Task not found. For id value: " + id));
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task saveSubTask(Task task) {
        if (!task.isClosed()) {
            updateParentRemainingTime(task,true);
        }
        saveTask(task);
        return task;
    }

    @Override
    @Transactional
    public void closeTask(Task task) {
        if (task.isClosed()) {
            return;
        }
        //decrease remaining time on parent task tree
        updateParentRemainingTime(task, false);

        //close task and sub tasks, lets assume that closing means isClosed = true and remainingTime = 0;
        closeTaskAndSubTasks(task);
        return;
    }
    @Override
    public void updateParentRemainingTime(Task task, boolean increase) {
        if (task.getTotalRemainingTime() > 0) {
            Task currentParentTask = task.getParentTask();
            while (currentParentTask != null) {
                currentParentTask.setTotalRemainingTime(currentParentTask.getTotalRemainingTime() + (increase ? 1:- 1)*task.getTotalRemainingTime());
                taskRepository.save(currentParentTask);
                currentParentTask = currentParentTask.getParentTask();
            }
        }
    }
    @Override
    public void closeTaskAndSubTasks(Task task) {
        task.setClosed(true);
        task.setTotalRemainingTime(0);
        taskRepository.save(task);
        for (Task subTask : task.getSubTasks()) {
            closeTaskAndSubTasks(subTask);
        }
    }

    @Override
    public void assignUserOnTask(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    @Override
    public List<Task> getUserTasksOnProjects(User user, List<Project> projects) {
        return taskRepository.findAllByProjectInAndUser(projects, user);
    }

    @Override
    public List<Task> projectTasks(Project project) {
        return taskRepository.findAllByProject(project);
    }


}
