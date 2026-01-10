package com.demo.taskflow.project;

import com.demo.taskflow.common.ResourceNotFoundException;
import com.demo.taskflow.project.dto.ProjectCreateRequest;
import com.demo.taskflow.project.dto.ProjectResponse;
import com.demo.taskflow.security.CurrentUser;
import com.demo.taskflow.user.User;
import com.demo.taskflow.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ProjectResponse createMyProject( ProjectCreateRequest project){
        User owner = userRepo.findByEmail(CurrentUser.getEmail()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Project p = Project.builder().name(project.getName()).description(project.getDescription()).owner(owner).build();
        return ProjectMapper.toResponse(projectRepository.save(p));
    }
    public List<ProjectResponse> listByUser(Long ownerId){

        User owner = userRepo.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return projectRepository.findById(ownerId).stream().map(ProjectMapper::toResponse).toList();
    }



    public Page<ProjectResponse> getMyProjects(String name, Pageable pageable){
        User user = userRepo.findByEmail(CurrentUser.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Page<Project> projects = (name == null)
                ? projectRepository.findByOwner(user, pageable)
                : projectRepository.findByOwnerAndNameContainingIgnoreCase(user, name, pageable);

        return projects.map(ProjectMapper::toResponse);

    }

}
