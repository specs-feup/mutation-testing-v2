package org.feup.Mutation_Testing_Backend_Final.Service.TestService;

import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.Project;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectMutantGeneration;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectMutantGenerationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class TestService {

    private final projectMutantGenerationRepository projectMutantGenerationRepository;
    private final MavenTestService mavenTestService;
    private final GradleTestService gradleTestService;
    private final AndroidTestService androidTestService;

    @Autowired
    public TestService(projectMutantGenerationRepository projectMutantGenerationRepository, MavenTestService mavenTestService, GradleTestService gradleTestService, AndroidTestService androidTestService) {
        this.projectMutantGenerationRepository = projectMutantGenerationRepository;
        this.mavenTestService = mavenTestService;
        this.gradleTestService = gradleTestService;
        this.androidTestService = androidTestService;
    }

    public SimpleResponse executeAllTests(String projectMutantGenerationId, String testExecutionTypeStr) {
        SimpleResponse sr = new SimpleResponse();

        Long i = Long.parseLong(projectMutantGenerationId);
        Optional<ProjectMutantGeneration> projectMutantGenerationOptional = projectMutantGenerationRepository.findById(i);

        ProjectTestExecution.TestExecutionType testExecutionType = ProjectTestExecution.TestExecutionType.valueOf(testExecutionTypeStr.toUpperCase());

        if (projectMutantGenerationOptional.isPresent()){
            ProjectMutantGeneration projectMutantGeneration = projectMutantGenerationOptional.get();
            Project project = projectMutantGeneration.getProjectVersion().getProject();

            //Maven project
            if (!project.isAndroid() && project.isMaven()) {
                return mavenTestService.ExecuteAllTests(testExecutionType, projectMutantGeneration);

            //Gradle Android App
            }else if (project.isAndroid()  && !project.isMaven()){
                return androidTestService.ExecuteAllTests(testExecutionType, projectMutantGeneration);

            //Gradle Project
            }else if (!project.isAndroid()  && !project.isMaven()) {
                return gradleTestService.ExecuteAllTests(testExecutionType, projectMutantGeneration);

            }else{
                sr.setAsError("No support for Android projects using Maven");
            }

        }else{
            sr.setAsError("Project Version Not Found");
        }
        return sr;
    }

    /*public SimpleResponse executeAllTestsGitImprovement(String projectVersionFromStr, String projectVersionToStr, String testExecutionType, List<MutationOperator> operatorList) throws Exception {
        SimpleResponse sr = new SimpleResponse();
        Long from = Long.parseLong(projectVersionFromStr);
        Long to = Long.parseLong(projectVersionToStr);
        TestExecutionType testExecutionTypeEnum = TestExecutionType.valueOf(testExecutionType.toUpperCase());

        Optional<ProjectVersion> projectVersionOptionalFrom = projectVersionRepository.findById(from);
        Optional<ProjectVersion> projectVersionOptionalTo = projectVersionRepository.findById(to);

        if (projectVersionOptionalFrom.isPresent() && projectVersionOptionalTo.isPresent()){
            ProjectVersion projectVersionFrom = projectVersionOptionalFrom.get();
            ProjectVersion projectVersionTo = projectVersionOptionalTo.get();

            //Maven project
            if (!projectVersionFrom.getProject().isAndroid() && projectVersionFrom.getProject().isMaven()){

                return mavenTestService.ExecuteAllTestsMavenGit(projectVersionFrom, projectVersionTo, testExecutionTypeEnum, operatorList);
                //Gradle Android App
                //Gradle Project
            }else if (!projectVersionFrom.getProject().isAndroid() && !projectVersionFrom.getProject().isMaven()) {
                return gradleTestService.ExecuteAllTestsGradleGit(projectVersionFrom, projectVersionTo, testExecutionTypeEnum, operatorList);
            }else{
                sr.setAsError("No support for Android projects using Maven");
            }

        }else{
            sr.setAsError("Project Version Not Found");
        }
        return sr;
    }*/

}
