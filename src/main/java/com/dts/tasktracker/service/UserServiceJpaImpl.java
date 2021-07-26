package com.dts.tasktracker.service;

import com.dts.tasktracker.exception.NotFoundException;
import com.dts.tasktracker.model.Project;
import com.dts.tasktracker.model.Task;
import com.dts.tasktracker.model.User;
import com.dts.tasktracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceJpaImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskService taskService;

    public UserServiceJpaImpl(UserRepository userRepository, TaskService taskService) {
        this.userRepository = userRepository;
        this.taskService = taskService;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found. For id value: " + id));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(User user) {
        for (Task t : user.getTaskList()) {
            t.setUser(null);
            taskService.saveTask(t);
        }
        userRepository.delete(user);
    }

    @Override
    public void assignUserOnProject(User user, Project project) {
        user.setProject(project);
        userRepository.save(user);
    }
}
