package org.feup.Mutation_Testing_Backend_Final.Model.Project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ProjectMutantExecution")
public class ProjectMutantGeneration {

    public enum MutationGenerationType{
        TRADITIONALMUTATION,
        MUTANTSCHEMATA,
        MUTANTSCHEMATA2,
        NOMUTATION,
        GITIMMPROVEMENTMUTANTSCHEMATA,
        GITIMMPROVEMENTTRADITIONALMUTATION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated
    @Column(nullable = false)
    private ProjectMutantGeneration.MutationGenerationType mutationGenerationType;
    private Float mutantGenerationTime;
    @Column(nullable = false)
    private String projectExecutionName;

    @OneToMany(mappedBy = "projectMutantGeneration")
    private List<MutationOperator> mutationOperators;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "projectVersion_id")
    private ProjectVersion projectVersion;

    @OneToMany(mappedBy = "projectMutantGeneration")
    private List<ProjectTestExecution> projectTestExecutions;

    public ProjectMutantGeneration() {
    }

    public ProjectMutantGeneration(ProjectMutantGeneration.MutationGenerationType mutationGenerationType, ProjectVersion projectVersion, String projectExecutionName) {
        this.mutationGenerationType = mutationGenerationType;
        this.projectVersion = projectVersion;
        this.projectExecutionName = projectExecutionName;
    }

    public Long getId() {
        return id;
    }

    public MutationGenerationType getMutationGenerationType() {
        return mutationGenerationType;
    }

    public Float getMutantGenerationTime() {
        return mutantGenerationTime;
    }

    public List<MutationOperator> getMutationOperators() {
        return mutationOperators;
    }

    public ProjectVersion getProjectVersion() {
        return projectVersion;
    }

    public void setMutationGenerationType(MutationGenerationType mutationGenerationType) {
        this.mutationGenerationType = mutationGenerationType;
    }

    public void setMutantGenerationTime(Float mutantGenerationTime) {
        this.mutantGenerationTime = mutantGenerationTime;
    }

    public void setMutationOperators(List<MutationOperator> mutationOperators) {
        this.mutationOperators = mutationOperators;
    }

    public void setProjectVersion(ProjectVersion projectVersion) {
        this.projectVersion = projectVersion;
    }

    public String getProjectExecutionName() {
        return projectExecutionName;
    }

    public void setProjectExecutionName(String projectExecutionName) {
        this.projectExecutionName = projectExecutionName;
    }
}
