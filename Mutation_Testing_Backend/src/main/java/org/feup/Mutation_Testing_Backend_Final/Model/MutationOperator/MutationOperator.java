package org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectMutantGeneration;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MutationOperator")
public class MutationOperator {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String Operador;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "projectMutantGeneration_id")
    private ProjectMutantGeneration projectMutantGeneration;

    @OneToMany(mappedBy = "mutationOperator")
    private List<MutationOperatorArguments> mutationOperatorArgumentsList;

    public MutationOperator() {
    }

    public MutationOperator(String operador, ProjectMutantGeneration projectMutantGeneration) {
        Operador = operador;
        this.projectMutantGeneration = projectMutantGeneration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperador() {
        return Operador;
    }

    public void setOperador(String operador) {
        Operador = operador;
    }

    public ProjectMutantGeneration getProjectMutantGeneration() {
        return projectMutantGeneration;
    }

    public void setProjectMutantGeneration(ProjectMutantGeneration projectMutantGeneration) {
        this.projectMutantGeneration = projectMutantGeneration;
    }

    public List<MutationOperatorArguments> getMutationOperatorArgumentsList() {
        return mutationOperatorArgumentsList;
    }

    public void setMutationOperatorArgumentsList(List<MutationOperatorArguments> mutationOperatorArgumentsList) {
        this.mutationOperatorArgumentsList = mutationOperatorArgumentsList;
    }
}
