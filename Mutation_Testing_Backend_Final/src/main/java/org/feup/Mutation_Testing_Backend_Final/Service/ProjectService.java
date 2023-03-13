package org.feup.Mutation_Testing_Backend_Final.Service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
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
import java.util.List;

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

        Ref head = git.getRepository().exactRef("HEAD");
        String branchName = head.getTarget().getName();

        RevWalk walk = new RevWalk(git.getRepository());
        ObjectId headObjectId = head.getObjectId();
        RevCommit headCommit = walk.parseCommit(headObjectId);

        System.out.println("Branch: " + branchName);
        System.out.println("Version: " + headCommit.getName());

        walk.close();
        git.close();


        Project savedProject = projectRepository.save(newProject);

        ProjectVersion projectVersion = new ProjectVersion();
        projectVersion.setVersion(headCommit.getName());
        projectVersion.setBranch(branchName);
        projectVersion.setProject(savedProject);
        projectVersionRepository.save(projectVersion);

        return savedProject;
    }


}
