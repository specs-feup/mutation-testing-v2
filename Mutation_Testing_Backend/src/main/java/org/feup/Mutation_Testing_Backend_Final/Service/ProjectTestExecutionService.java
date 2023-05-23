package org.feup.Mutation_Testing_Backend_Final.Service;

import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectTestExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectTestExecutionService {
    private final projectTestExecutionRepository projectTestExecutionRepository;

    @Autowired
    public ProjectTestExecutionService(projectTestExecutionRepository projectTestExecutionRepository) {
        this.projectTestExecutionRepository = projectTestExecutionRepository;
    }

    public ProjectTestExecution getProjectTestExecution(Long id){
        Optional<ProjectTestExecution> projectTestExecutionOptional = projectTestExecutionRepository.findById(id);

        if (projectTestExecutionOptional.isPresent()){
            ProjectTestExecution projectTestExecution = projectTestExecutionOptional.get();
            return projectTestExecution;
        }
        return null;
    }
}
