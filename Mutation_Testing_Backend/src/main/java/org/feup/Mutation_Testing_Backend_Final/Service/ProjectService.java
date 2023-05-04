package org.feup.Mutation_Testing_Backend_Final.Service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.feup.Mutation_Testing_Backend_Final.Helper.Githelper;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.Project;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectVersion;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    //Value from application.properties
    @Value("${pathToStoreProjects}")
    private String projectsPath;

    private final projectRepository projectRepository;
    private final projectVersionRepository projectVersionRepository;

    @Autowired
    public ProjectService(projectRepository projectRepository, projectVersionRepository projectVersionRepository) {
        this.projectRepository = projectRepository;
        this.projectVersionRepository = projectVersionRepository;
    }

    public List<Project> getAllProjects() {
        List<Project> listaProjetos = new ArrayList<>();

        projectRepository.findAll().forEach(listaProjetos::add);

        return listaProjetos;
    }


    public Project addNewProject(Project newProject) throws GitAPIException, IOException {
        // Clone Repo
        Git gitRepo = Githelper.cloneRepo(newProject.getProjectUrl(), projectsPath + newProject.getProjectPath());

        // Get Repo Versions
        List<ProjectVersion> projectVersionList = Githelper.getCommitsHistory(gitRepo, newProject);

        // Save Repo and Repo Versions
        newProject.setProjectVersions(projectVersionList);
        Project savedProject = projectRepository.save(newProject);
        projectVersionRepository.saveAll(projectVersionList);

        return savedProject;
    }


    public List<ProjectVersion> getProjectVersions(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()){
            return optionalProject.get().getProjectVersions();
        }else{
            return Collections.emptyList();
        }
    }

}
