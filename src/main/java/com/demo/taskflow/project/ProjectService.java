package com.demo.taskflow.project;

import com.demo.taskflow.project.dto.ProjectCreateRequest;
import com.demo.taskflow.project.dto.ProjectResponse;
import com.demo.taskflow.user.User;
import com.demo.taskflow.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepo;
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepo) {
        this.projectRepository = projectRepository;
        this.userRepo = userRepo;
    }
    public ProjectResponse create(Long ownerId, ProjectCreateRequest project){
        User owner = userRepo.findById(ownerId).orElseThrow();
        Project p = Project.builder().name(project.getName()).description(project.getDescription()).owner(owner).build();
        return ProjectMapper.toResponse(projectRepository.save(p));
    }
    public List<ProjectResponse> listByUser(Long ownerId){
        if( !userRepo.existsById(ownerId)){
            throw new RuntimeException("User not found");
        }
        return projectRepository.findById(ownerId).stream().map(ProjectMapper::toResponse).toList();
    }

}
