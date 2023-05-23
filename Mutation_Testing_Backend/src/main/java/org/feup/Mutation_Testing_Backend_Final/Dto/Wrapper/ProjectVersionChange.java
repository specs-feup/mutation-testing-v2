package org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper;

public class ProjectVersionChange {
    private String projectVersion;
    private int filesAdded;
    private int javaFilesAdded;
    private int filesRemoved;
    private int javaFilesRemoved;
    private int filesChanged;
    private int javaFilesChanged;
    private int totalNumberOfFiles;
    private int totalNumberOfJavaFiles;

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public int getFilesAdded() {
        return filesAdded;
    }

    public void setFilesAdded(int filesAdded) {
        this.filesAdded = filesAdded;
    }

    public int getJavaFilesAdded() {
        return javaFilesAdded;
    }

    public void setJavaFilesAdded(int javaFilesAdded) {
        this.javaFilesAdded = javaFilesAdded;
    }

    public int getFilesRemoved() {
        return filesRemoved;
    }

    public void setFilesRemoved(int filesRemoved) {
        this.filesRemoved = filesRemoved;
    }

    public int getJavaFilesRemoved() {
        return javaFilesRemoved;
    }

    public void setJavaFilesRemoved(int javaFilesRemoved) {
        this.javaFilesRemoved = javaFilesRemoved;
    }

    public int getFilesChanged() {
        return filesChanged;
    }

    public void setFilesChanged(int filesChanged) {
        this.filesChanged = filesChanged;
    }

    public int getJavaFilesChanged() {
        return javaFilesChanged;
    }

    public void setJavaFilesChanged(int javaFilesChanged) {
        this.javaFilesChanged = javaFilesChanged;
    }

    public int getTotalNumberOfFiles() {
        return totalNumberOfFiles;
    }

    public void setTotalNumberOfFiles(int totalNumberOfFiles) {
        this.totalNumberOfFiles = totalNumberOfFiles;
    }

    public int getTotalNumberOfJavaFiles() {
        return totalNumberOfJavaFiles;
    }

    public void setTotalNumberOfJavaFiles(int totalNumberOfJavaFiles) {
        this.totalNumberOfJavaFiles = totalNumberOfJavaFiles;
    }
}
