package com.demo.taskflow.project;

import com.demo.taskflow.user.User;
import com.demo.taskflow.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepo;
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepo) {
        this.projectRepository = projectRepository;
        this.userRepo = userRepo;
    }
    public Project create(Long ownerId, Project project){
        User owner = userRepo.findById(ownerId).orElseThrow();
        project.setOwner(owner);
        return projectRepository.save(project);
    }
    public List<Project> list(Long ownerId){
        if( !userRepo.existsById(ownerId)){
            throw new RuntimeException("User not found");
        }
        return projectRepository.findByOwnerId(ownerId);
    }

}
