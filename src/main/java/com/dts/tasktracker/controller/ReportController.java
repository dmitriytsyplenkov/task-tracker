package com.dts.tasktracker.controller;


import com.dts.tasktracker.model.Task;
import com.dts.tasktracker.service.ProjectService;
import com.dts.tasktracker.service.TaskService;
import com.dts.tasktracker.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    public ReportController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("")
    public List<Task> getUserTasksOnProjects(@RequestParam(name = "project_ids", defaultValue = "0") String[] projectIds, @RequestParam(name = "user_id",defaultValue = "0") String userId) {

        return taskService.getUserTasksOnProjects(userService.findById(Long.valueOf(userId)),
                projectService.getProjectsByIds(Arrays.stream(projectIds).map(Long::valueOf).collect(Collectors.toList())));

    }

}
