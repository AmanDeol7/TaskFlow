package com.demo.taskflow.project;

import com.demo.taskflow.project.dto.ProjectCreateRequest;
import com.demo.taskflow.project.dto.ProjectResponse;
import com.demo.taskflow.user.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.net.URI;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/users/{userId}/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ProjectResponse create(@PathVariable User userId, @Valid @RequestBody ProjectCreateRequest req){
        return projectService.create(userId.getId(), req);


    }

    @GetMapping
    public List<ProjectResponse> list(@PathVariable Long userId){
        return projectService.listByUser(userId);
    }


}
