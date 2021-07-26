package com.dts.tasktracker.controller;

import com.dts.tasktracker.model.Project;
import com.dts.tasktracker.service.ProjectService;
import com.dts.tasktracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> projectList() {
        return projectService.getProjects();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project projectInfo(@PathVariable String id) {
        return projectService.findById(Long.valueOf(id));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void newProject(@RequestBody Project project) {
        projectService.saveProject(project);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id) {
        projectService.delete(projectService.findById(Long.valueOf(id)));
    }

    @PutMapping("/{projectId}/users/{userId}/assign")
    @ResponseStatus(HttpStatus.OK)
    public void assignUserOnProject(@PathVariable String projectId, @PathVariable String userId) {
        projectService.validateProject(Long.valueOf(projectId));
        userService.assignUserOnProject(userService.findById(Long.valueOf(userId)), projectService.findById(Long.valueOf(projectId)));

    }
}
