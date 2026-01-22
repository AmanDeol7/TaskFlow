package com.demo.taskflow.attachment;

import com.demo.taskflow.common.ResourceNotFoundException;
import com.demo.taskflow.project.Project;
import com.demo.taskflow.project.ProjectRepository;
import com.demo.taskflow.security.CurrentUser;
import com.demo.taskflow.user.User;
import com.demo.taskflow.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;


@Service
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final ProjectRepository projectRepository;
    private final FileStorageService fileStorageService;
    private final UserRepository userRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, ProjectRepository projectRepository, FileStorageService fileStorageService, UserRepository userRepository) {
        this.attachmentRepository = attachmentRepository;
        this.projectRepository = projectRepository;
        this.fileStorageService = fileStorageService;
        this.userRepository = userRepository;
    }

    public void upload(Long projectId, MultipartFile file) throws IOException {
        String email = CurrentUser.getEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        System.out.println(">>> User ID: " + user.getId());
        System.out.println(">>> Looking for project with ID: " + projectId + " and owner ID: " + user.getId());

        Project project = projectRepository
                .findByIdAndOwnerId(projectId, user.getId())
                .orElseThrow(() -> new AccessDeniedException("Not your project"));
        System.out.println(">>> Project found: " + project.getName());

        String storedName = fileStorageService.save(file);

        Attachment attachment = Attachment.builder()
                .originalFileName(file.getOriginalFilename())
                .storedFilename(storedName)
                .contentType(file.getContentType())
                .size(file.getSize())
                .project(project)
                .build();

        attachmentRepository.save(attachment);
    }


}
