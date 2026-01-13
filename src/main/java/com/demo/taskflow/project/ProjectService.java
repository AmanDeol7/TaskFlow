package com.demo.taskflow.project;

import com.demo.taskflow.common.ResourceNotFoundException;
import com.demo.taskflow.project.dto.ProjectCreateRequest;
import com.demo.taskflow.project.dto.ProjectResponse;
import com.demo.taskflow.project.spec.ProjectSpecifications;
import com.demo.taskflow.security.CurrentUser;
import com.demo.taskflow.user.User;
import com.demo.taskflow.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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



    public Page<ProjectResponse> getMyProjects(String name, LocalDate fromDate , LocalDate toDate, Pageable pageable){
        User user = userRepo.findByEmail(CurrentUser.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        LocalDateTime from = fromDate != null ? fromDate.atStartOfDay() : null;
        LocalDateTime to = toDate != null ? toDate.plusDays(1).atStartOfDay() : null;


        Specification<Project> spec =
                Specification.where(ProjectSpecifications.ownedBy(user))
                        .and(ProjectSpecifications.nameContains(name))
                        .and(ProjectSpecifications.createdBetween(from, to));

        return projectRepository.findAll(spec, pageable).map(ProjectMapper::toResponse);



    }

}
