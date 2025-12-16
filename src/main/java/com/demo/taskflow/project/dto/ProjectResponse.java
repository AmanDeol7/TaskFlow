package com.demo.taskflow.project.dto;


import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;

}


