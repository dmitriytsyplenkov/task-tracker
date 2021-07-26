package com.dts.tasktracker.controller;

import com.dts.tasktracker.model.Task;
import com.dts.tasktracker.service.ProjectService;
import com.dts.tasktracker.service.TaskService;
import com.dts.tasktracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(ProjectService projectService, TaskService taskService, UserService userService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String projectId, @PathVariable String taskId) {
        //check that project with specified id exists if not we will throw exception
        projectService.findById(Long.valueOf(projectId));
        taskService.delete(taskService.findById(Long.valueOf(taskId)));

    }
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> projectTasks(@PathVariable String projectId) {
        return taskService.projectTasks(projectService.findById(Long.valueOf(projectId)));
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void newTask(@PathVariable String projectId, @RequestBody Task task) {
        task.setProject(projectService.findById(Long.valueOf(projectId)));
        taskService.saveTask(task);
    }

    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task taskInfo(@PathVariable String projectId, @PathVariable String taskId) {
        projectService.validateProject(Long.valueOf(projectId));
        return taskService.findById(Long.valueOf(taskId));

    }

    @PutMapping("/{taskId}/close")
    @ResponseStatus(HttpStatus.OK)
    public void closeTask(@PathVariable String projectId, @PathVariable String taskId) {
        projectService.validateProject(Long.valueOf(projectId));
        taskService.closeTask(taskService.findById(Long.valueOf(taskId)));
    }
    @PostMapping("/{taskId}/subtasks")
    @ResponseStatus(HttpStatus.CREATED)
    public void newSubTask(@PathVariable String projectId, @PathVariable String taskId, @RequestBody Task task) {
        task.setProject(projectService.findById(Long.valueOf(projectId)));
        task.setParentTask(taskService.findById(Long.valueOf(taskId)));
        if (task.getId().equals(task.getParentTask().getId())) {
            throw new IllegalArgumentException("task and sub task id couldn't match!!!");
        }
        taskService.saveSubTask(task);
    }

    @PutMapping("/{taskId}/users/{userId}/assign")
    @ResponseStatus(HttpStatus.OK)
    public void assignUserOnTask(@PathVariable String projectId, @PathVariable String taskId, @PathVariable String userId) {
        projectService.validateProject(Long.valueOf(projectId));
        taskService.assignUserOnTask(taskService.findById(Long.valueOf(taskId)), userService.findById(Long.valueOf(userId)));
    }


}
