package com.dts.tasktracker.service;

import com.dts.tasktracker.model.Project;

import java.util.List;

public interface ProjectService {

    Project saveProject(Project project);

    void delete(Project project);

    Project findById(Long id);

    List<Project> getProjects();

    void validateProject(Long id);

    List<Project> getProjectsByIds(List<Long> ids);
}
