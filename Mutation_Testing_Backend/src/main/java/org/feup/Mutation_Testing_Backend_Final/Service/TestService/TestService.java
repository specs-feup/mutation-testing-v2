package org.feup.Mutation_Testing_Backend_Final.Service.TestService;

import org.apache.maven.plugin.surefire.log.api.NullConsoleLogger;
import org.apache.maven.plugins.surefire.report.ReportTestCase;
import org.apache.maven.plugins.surefire.report.ReportTestSuite;
import org.apache.maven.plugins.surefire.report.SurefireReportParser;
import org.apache.maven.reporting.MavenReportException;
import org.eclipse.jgit.api.errors.GitAPIException;
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
    @Value("${pathToStoreProjects}")
    private String projectsPath;
    @Value("${pathToKadabraIncludes}")
    private String pathToKadabraIncludes;
    @Value("${pathToKadabraEntryPoint}")
    private String pathToKadabraEntryPoint;

    private final projectVersionRepository projectVersionRepository;
    private final MavenTestService mavenTestService;
    private final GradleTestService gradleTestService;

    @Autowired
    public TestService(projectVersionRepository projectVersionRepository, MavenTestService mavenTestService, GradleTestService gradleTestService) {
        this.projectVersionRepository = projectVersionRepository;
        this.mavenTestService = mavenTestService;
        this.gradleTestService = gradleTestService;
    }

    public String executeAllTests(String projectVersionId, String testExecutionType, List<MutationOperator> operatorList) throws Exception {
        Long i = Long.parseLong(projectVersionId);
        TestExecutionType testExecutionTypeEnum = TestExecutionType.valueOf(testExecutionType.toUpperCase());

        Optional<ProjectVersion> projectVersionOptional = projectVersionRepository.findById(i);

        if (projectVersionOptional.isPresent()){
            ProjectVersion projectVersion = projectVersionOptional.get();

            System.out.println("Entrou Antes");
            System.out.println(!projectVersion.getProject().isAndroid());
            System.out.println(!projectVersion.getProject().isMaven());

            //Maven project
            if (!projectVersion.getProject().isAndroid() && projectVersion.getProject().isMaven()){
                mavenTestService.ExecuteAllTestsMaven(projectVersion, testExecutionTypeEnum, operatorList);
                return "";
            //Gradle Android App
            } else if (projectVersion.getProject().isAndroid() && !projectVersion.getProject().isMaven()) {
                ExecuteAllTestsAndroid();
            //Gradle Project
            }else if (!projectVersion.getProject().isAndroid() && !projectVersion.getProject().isMaven()) {
                gradleTestService.ExecuteAllTestsGradle(projectVersion, testExecutionTypeEnum, operatorList);
            }else{
                return "Erro";
            }

        }
        return "Project Version Not Found";
    }

    private void ExecuteAllTestsAndroid(){

    }

    public void getDiferences() throws GitAPIException, IOException {
        Optional<ProjectVersion> projectVersionOptional = projectVersionRepository.findById(63L);
        Optional<ProjectVersion> projectVersionOptionalOlder = projectVersionRepository.findById(62L);

        if (projectVersionOptional.isPresent() && projectVersionOptionalOlder.isPresent()){
            ProjectVersion projectVersion = projectVersionOptional.get();
            ProjectVersion projectVersionOlder = projectVersionOptionalOlder.get();

            List<String> lista = Githelper.getChangedFiles(projectVersionOlder.getVersion(), projectVersion.getVersion(), projectsPath + projectVersion.getProject().getProjectPath());

            for (String fileName:lista) {
                System.out.println(fileName);
            }
        }
    }
}
