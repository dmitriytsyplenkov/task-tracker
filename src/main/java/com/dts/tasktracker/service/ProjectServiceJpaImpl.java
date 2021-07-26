package com.dts.tasktracker.service;

import com.dts.tasktracker.exception.NotFoundException;
import com.dts.tasktracker.model.Project;
import com.dts.tasktracker.model.User;
import com.dts.tasktracker.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectServiceJpaImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ProjectServiceJpaImpl(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }


    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public void delete(Project project) {
        for (User user : project.getUserList()) {
            user.setProject(null);
            userService.saveUser(user);
        }
        projectRepository.delete(project);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(()->new NotFoundException("Project not found. For id value: " + id));
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void validateProject(Long id) {
        findById(id);
    }

    @Override
    public List<Project> getProjectsByIds(List<Long> ids) {
        return projectRepository.findAllById(ids);
    }


}
