package org.feup.Mutation_Testing_Backend_Final.Service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.feup.Mutation_Testing_Backend_Final.Model.Project;
import org.feup.Mutation_Testing_Backend_Final.Model.ProjectVersion;
import org.feup.Mutation_Testing_Backend_Final.Repository.projectRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.projectVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
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
        Git git = Git.cloneRepository()
                .setURI(newProject.getProjectUrl())
                .setDirectory(new File(projectsPath + newProject.getProjectPath()))
                .call();

        // Get all the refs (branches, tags, etc.) in the repository
        List<Ref> refs = git.tagList().call();
        refs.addAll(git.branchList().call());

        // Create a list to hold the commit SHAs
        List<String> commitSHAs = new ArrayList<>();

        Project savedProject = projectRepository.save(newProject);

        // Loop through all the refs and add their commit SHAs to the list
        for (Ref ref : refs) {
            commitSHAs.add(ref.getObjectId().getName());

            ProjectVersion projectVersion = new ProjectVersion();
            projectVersion.setVersion(ref.getObjectId().getName());
            projectVersion.setBranch(ref.getTarget().getName());
            projectVersion.setProject(savedProject);
            projectVersionRepository.save(projectVersion);
        }

        git.close();

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

    public Project setProjectVersion(Project project) {
        Optional<Project> optionalProject = projectRepository.findById(project.getId());

        //falta verificar as versões

        return optionalProject.get();
    }
}
