package com.demo.taskflow.project;

import com.demo.taskflow.user.User;
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
    public Project create(@PathVariable User userId, @RequestBody Project project){
        return projectService.create(userId.getId(), project);


    }

    @GetMapping
    public List<Project> list(@PathVariable Long userId){
        return projectService.list(userId);
    }


}
