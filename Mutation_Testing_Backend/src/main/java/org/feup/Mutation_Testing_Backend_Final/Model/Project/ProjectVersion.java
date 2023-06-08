package org.feup.Mutation_Testing_Backend_Final.Model.Project;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ProjectVersion")
public class ProjectVersion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String version;
    private String branch;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "projectVersion")
    private List<ProjectMutantGeneration> projectMutantGenerations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ProjectMutantGeneration> getProjectMutantGenerations() {
        return projectMutantGenerations;
    }

    public void setProjectMutantGenerations(List<ProjectMutantGeneration> projectMutantGenerations) {
        this.projectMutantGenerations = projectMutantGenerations;
    }

    @Override
    public String toString() {
        return "ProjectVersion{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
