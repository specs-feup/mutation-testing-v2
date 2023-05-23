package org.feup.Mutation_Testing_Backend_Final.Service.TestService;

import org.apache.maven.plugin.surefire.log.api.NullConsoleLogger;
import org.apache.maven.plugins.surefire.report.ReportTestCase;
import org.apache.maven.plugins.surefire.report.ReportTestSuite;
import org.apache.maven.plugins.surefire.report.SurefireReportParser;
import org.apache.maven.reporting.MavenReportException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Helper.Githelper;
import org.feup.Mutation_Testing_Backend_Final.Helper.KadabraHelper;
import org.feup.Mutation_Testing_Backend_Final.Helper.OperatorValidator;
import org.feup.Mutation_Testing_Backend_Final.Helper.OutputParsingHelper;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperator;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperatorArguments;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution.TestExecutionType;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectVersion;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestClass;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestPackage;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestUnit;
import org.feup.Mutation_Testing_Backend_Final.Repository.MutationOperator.mutationOperatorArgumentsRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectTestExecutionRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Test.testPackageRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.MutationOperator.mutationOperatorRepository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectVersionRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Test.*;


import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class TestService {

    private final projectVersionRepository projectVersionRepository;
    private final MavenTestService mavenTestService;
    private final GradleTestService gradleTestService;

    @Autowired
    public TestService(projectVersionRepository projectVersionRepository, MavenTestService mavenTestService, GradleTestService gradleTestService) {
        this.projectVersionRepository = projectVersionRepository;
        this.mavenTestService = mavenTestService;
        this.gradleTestService = gradleTestService;
    }

    public SimpleResponse executeAllTests(String projectVersionId, String testExecutionType, List<MutationOperator> operatorList) throws Exception {
        SimpleResponse sr = new SimpleResponse();
        Long i = Long.parseLong(projectVersionId);
        TestExecutionType testExecutionTypeEnum = TestExecutionType.valueOf(testExecutionType.toUpperCase());

        Optional<ProjectVersion> projectVersionOptional = projectVersionRepository.findById(i);

        if (projectVersionOptional.isPresent()){
            ProjectVersion projectVersion = projectVersionOptional.get();

            //Maven project
            if (!projectVersion.getProject().isAndroid() && projectVersion.getProject().isMaven()){
                return mavenTestService.ExecuteAllTestsMaven(projectVersion, testExecutionTypeEnum, operatorList);
            //Gradle Android App
            //Gradle Project
            }else if (!projectVersion.getProject().isMaven()) {
                return gradleTestService.ExecuteAllTestsGradle(projectVersion, testExecutionTypeEnum, operatorList);
            }else{
                sr.setAsError("No support for Android projects using Maven");
            }

        }else{
            sr.setAsError("Project Version Not Found");
        }
        return sr;
    }

    public SimpleResponse executeAllTestsGitImprovement(String projectVersionFromStr, String projectVersionToStr, String testExecutionType, List<MutationOperator> operatorList) throws Exception {
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
    }

}
