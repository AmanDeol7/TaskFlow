package com.demo.taskflow.project;
import com.demo.taskflow.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long>{
    Page<Project> findByOwner(User owner, Pageable pageable);
    //Pageable means instructions to database
    // spring will automatically convert pageable to limit + offset
    //Page<T> means list of items + pagination metadata

    Page<Project> findByOwnerAndNameContainingIgnoreCase(User owner, String name, Pageable pageable);
}
