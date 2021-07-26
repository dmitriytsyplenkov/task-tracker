package com.dts.tasktracker.service;

import com.dts.tasktracker.model.Project;
import com.dts.tasktracker.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User findById(Long id);

    List<User> getUsers();

    void delete(User user);

    void assignUserOnProject(User user, Project project);
}
