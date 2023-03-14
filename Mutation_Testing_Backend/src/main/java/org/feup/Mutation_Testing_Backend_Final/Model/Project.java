package org.feup.Mutation_Testing_Backend_Final.Model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="Project")
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String projectUrl;

    @NotBlank
    private String projectName;

    @NotBlank
    private String projectPath;
    private boolean maven;

    @OneToMany(mappedBy = "project")
    private List<ProjectVersion> projectVersions;

    private long currentProjectVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public boolean isMaven() {
        return maven;
    }

    public void setMaven(boolean maven) {
        this.maven = maven;
    }

    public List<ProjectVersion> getProjectVersions() {
        return projectVersions;
    }

    public void setProjectVersions(List<ProjectVersion> projectVersions) {
        this.projectVersions = projectVersions;
    }

    public long getCurrentProjectVersion() {
        return currentProjectVersion;
    }

    public void setCurrentProjectVersion(long currentProjectVersion) {
        this.currentProjectVersion = currentProjectVersion;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectUrl='" + projectUrl + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectPath='" + projectPath + '\'' +
                ", maven=" + maven +
                ", projectVersions=" + projectVersions +
                '}';
    }
}
