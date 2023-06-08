package org.feup.Mutation_Testing_Backend_Final.Model.Project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperator;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestClass;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestPackage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ProjectTestExecution")
public class ProjectTestExecution {


    public enum TestExecutionType{
        NORMAL,
        PARALLEL
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private TestExecutionType testExecutionType;
    private Float elaspedTime;
    private Float testRunTime;
    private boolean failedCompilation;
    private boolean failedTests;
    private int mutationLine;
    private String mutationFilePath;
    private String mutantId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "projectMutantGeneration_id")
    private ProjectMutantGeneration projectMutantGeneration;

    @OneToMany(mappedBy = "projectTestExecution", cascade = CascadeType.ALL)
    private List<ProjectTestExecution> ProjectTestExecutionSubExecutions = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "id")
    private ProjectTestExecution projectTestExecution;

    @OneToMany(mappedBy = "projectTestExecution")
    @JsonIgnore
    private List<TestPackage> testPackage;

    public ProjectTestExecution() {
    }

    public ProjectTestExecution(TestExecutionType testExecutionType, ProjectMutantGeneration projectMutantGeneration) {
        this.testExecutionType = testExecutionType;
        this.projectMutantGeneration = projectMutantGeneration;
        this.mutationLine = -1;
        this.failedCompilation = false;

    }

    public ProjectTestExecution(ProjectTestExecution projectTestExecution, int mutationLine, String mutationFilePath, String mutantId, Float testRunTime) {
        this.projectTestExecution = projectTestExecution;
        this.mutationLine = mutationLine;
        this.mutationFilePath = mutationFilePath;
        this.mutantId = mutantId;
        this.testRunTime = testRunTime;
    }

    /*public ProjectTestExecution(TestExecutionType testExecutionType, ProjectVersion projectVersion) {
        this.testExecutionType = testExecutionType;
        //this.projectVersion = projectVersion;
        this.failedCompilation = true;
    }

    public ProjectTestExecution(TestExecutionType testExecutionType, ProjectVersion projectVersion, String projectExecutionName) {
        this.testExecutionType = testExecutionType;
        //this.projectVersion = projectVersion;
        this.failedCompilation = false;
    }

    public ProjectTestExecution(TestExecutionType testExecutionType, ProjectVersion projectVersion, ProjectTestExecution projectTestExecution, int mutationLine, String mutationFilePath,  String mutantId) {
        this.testExecutionType = testExecutionType;
        //this.projectVersion = projectVersion;
        this.projectTestExecution = projectTestExecution;
        this.mutationLine = mutationLine;
        this.mutationFilePath =mutationFilePath;
        this.mutantId = mutantId;
        this.failedCompilation = true;
    }


    public ProjectTestExecution(TestExecutionType testExecutionType, Float compilationTime, ProjectVersion projectVersion, Float testRunTime) {
        this.testExecutionType = testExecutionType;
        this.compilationTime = compilationTime;
        //this.projectVersion = projectVersion;
        this.testRunTime = testRunTime;
        this.failedCompilation = true;
    }

    public ProjectTestExecution(TestExecutionType testExecutionType, Float compilationTime, ProjectVersion projectVersion, Float testRunTime, ProjectTestExecution projectTestExecution) {
        this.testExecutionType = testExecutionType;
        this.compilationTime = compilationTime;
        //this.projectVersion = projectVersion;
        this.testRunTime = testRunTime;
        this.projectTestExecution = projectTestExecution;
        this.failedCompilation = true;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestExecutionType getTestExecutionType() {
        return testExecutionType;
    }

    public void setTestExecutionType(TestExecutionType testExecutionType) {
        this.testExecutionType = testExecutionType;
    }

    public Float getElaspedTime() {
        return elaspedTime;
    }

    public void setElaspedTime(Float elaspedTime) {
        this.elaspedTime = elaspedTime;
    }

    public Float getTestRunTime() {
        return testRunTime;
    }

    public void setTestRunTime(Float testRunTime) {
        this.testRunTime = testRunTime;
    }


    public ProjectMutantGeneration getProjectMutantGeneration() {
        return projectMutantGeneration;
    }

    public void setProjectMutantGeneration(ProjectMutantGeneration projectMutantGeneration) {
        this.projectMutantGeneration = projectMutantGeneration;
    }

    public List<ProjectTestExecution> getProjectTestExecutionSubExecutions() {
        return ProjectTestExecutionSubExecutions;
    }

    public void setProjectTestExecutionSubExecutions(List<ProjectTestExecution> projectTestExecutionSubExecutions) {
        ProjectTestExecutionSubExecutions = projectTestExecutionSubExecutions;
    }

    public ProjectTestExecution getProjectTestExecution() {
        return projectTestExecution;
    }

    public void setProjectTestExecution(ProjectTestExecution projectTestExecution) {
        this.projectTestExecution = projectTestExecution;
    }

    public List<TestPackage> getTestPackage() {
        return testPackage;
    }

    public void setTestPackage(List<TestPackage> testPackage) {
        this.testPackage = testPackage;
    }

    public boolean isFailedTests() {
        return failedTests;
    }

    public void setFailedTests(boolean failedTests) {
        this.failedTests = failedTests;
    }

    public boolean isFailedCompilation() {
        return failedCompilation;
    }

    public void setFailedCompilation(boolean failedCompilation) {
        this.failedCompilation = failedCompilation;
    }

    public int getMutationLine() {
        return mutationLine;
    }

    public void setMutationLine(int mutationLine) {
        this.mutationLine = mutationLine;
    }

    public String getMutationFilePath() {
        return mutationFilePath;
    }

    public void setMutationFilePath(String mutationFilePath) {
        this.mutationFilePath = mutationFilePath;
    }

    public String getMutantId() {
        return mutantId;
    }

    public void setMutantId(String mutantId) {
        this.mutantId = mutantId;
    }


    @Override
    public String toString() {
        return "ProjectTestExecution{" +
                "id=" + id +
                ", testExecutionType=" + testExecutionType +
                ", compilationTime=" + elaspedTime +
                ", testRunTime=" + testRunTime +
                ", failedCompilation=" + failedCompilation +
                ", failedTests=" + failedTests +
                ", mutationLine=" + mutationLine +
                ", mutationFilePath='" + mutationFilePath + '\'' +
                ", mutantId='" + mutantId + '\'' +
                ", ProjectTestExecutionSubExecutions=" + ProjectTestExecutionSubExecutions +
                ", projectTestExecution=" + projectTestExecution +
                ", testPackage=" + testPackage +
                '}';
    }
}

