package org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;

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
    @JoinColumn(name = "projectTestExecution_id")
    private ProjectTestExecution projectTestExecution;

    @OneToMany(mappedBy = "mutationOperator")
    private List<MutationOperatorArguments> mutationOperatorArgumentsList;

    public MutationOperator() {
    }

    public MutationOperator(String operador, ProjectTestExecution projectTestExecution) {
        Operador = operador;
        this.projectTestExecution = projectTestExecution;
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

    public ProjectTestExecution getProjectTestExecution() {
        return projectTestExecution;
    }

    public void setProjectTestExecution(ProjectTestExecution projectTestExecution) {
        this.projectTestExecution = projectTestExecution;
    }

    public List<MutationOperatorArguments> getMutationOperatorArgumentsList() {
        return mutationOperatorArgumentsList;
    }

    public void setMutationOperatorArgumentsList(List<MutationOperatorArguments> mutationOperatorArgumentsList) {
        this.mutationOperatorArgumentsList = mutationOperatorArgumentsList;
    }
}
