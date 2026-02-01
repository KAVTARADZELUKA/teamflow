package com.teamflow.backend.repository;

import com.teamflow.backend.model.Project;
import com.teamflow.backend.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByWorkspace(Workspace workspace);
}
