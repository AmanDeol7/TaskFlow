package com.demo.taskflow.user;
import com.demo.taskflow.project.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

}
