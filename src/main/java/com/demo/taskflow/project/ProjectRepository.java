    package com.demo.taskflow.project;
    import com.demo.taskflow.user.User;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Optional;


    public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
//        Page<Project> findByOwner(User owner, Pageable pageable);
//        //Pageable means instructions to database
//        // spring will automatically convert pageable to limit + offset
//        //Page<T> means list of items + pagination metadata
//
//        Page<Project>findByOwnerAndCreatedAtBetween(User owner, LocalDateTime from, LocalDateTime to,  Pageable pageable);
//
//        Page<Project> findByOwnerAndNameContainingIgnoreCase(User owner, String name, Pageable pageable);
//        Page<Project> findByOwnerAndNameContainingIgnoreCaseAndCreatedAtBetween(User owner, String name, LocalDateTime from , LocalDateTime to,  Pageable pageable);
        Optional<Project> findByIdAndOwner(Long id, User owner);
        Optional<Project> findByIdAndOwnerId(Long id, Long ownerId);
    }
