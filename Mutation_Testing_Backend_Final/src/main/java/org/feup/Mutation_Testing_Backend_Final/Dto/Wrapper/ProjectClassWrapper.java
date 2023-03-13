package org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper;

public class ProjectClassWrapper {
    private String projectPath;
    private String testClass;

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getTestClass() {
        return testClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }
}
