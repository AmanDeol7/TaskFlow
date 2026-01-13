package com.demo.taskflow.project;

import com.demo.taskflow.project.dto.ProjectCreateRequest;
import com.demo.taskflow.project.dto.ProjectResponse;
import com.demo.taskflow.user.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.net.URI;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/me/projects")
@PreAuthorize("hasRole('USER')")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ProjectResponse create( @Valid @RequestBody ProjectCreateRequest req){
        return projectService.createMyProject( req);


    }

    @GetMapping
    public Page<ProjectResponse> list(@RequestParam(required = false) String name,
                                      @RequestParam(required = false)
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                      LocalDate from,
                                      @RequestParam(required = false)
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                          LocalDate to ,

                                      @PageableDefault(size = 10,sort = "id")Pageable pageable){

        return projectService.getMyProjects(name,from , to, pageable);
    }


}
