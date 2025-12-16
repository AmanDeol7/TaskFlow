package com.demo.taskflow.project;

import com.demo.taskflow.project.dto.ProjectResponse;

public class ProjectMapper {
    public static ProjectResponse toResponse(Project project){
        return ProjectResponse.builder().id(project.getId()).name(project.getName()).description(project.getDescription()).ownerId(project.getOwner().getId()).build();


    }
    

}
