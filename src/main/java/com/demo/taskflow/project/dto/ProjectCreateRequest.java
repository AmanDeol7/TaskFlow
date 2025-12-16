package com.demo.taskflow.project.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Getter
@Setter
public class ProjectCreateRequest {
    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max=500)
    private String description;

}
