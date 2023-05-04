package org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="MutationOperatorArguments")
public class MutationOperatorArguments {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String mutationOperatorArgument;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "mutationOperator_id")
    private MutationOperator mutationOperator;

    public MutationOperatorArguments() {
    }

    public MutationOperatorArguments(String mutationOperatorArgument, MutationOperator mutationOperator) {
        this.mutationOperatorArgument = mutationOperatorArgument;
        this.mutationOperator = mutationOperator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMutationOperatorArgument() {
        return mutationOperatorArgument;
    }

    public void setMutationOperatorArgument(String mutationOperatorArgument) {
        this.mutationOperatorArgument = mutationOperatorArgument;
    }

    public MutationOperator getMutationOperator() {
        return mutationOperator;
    }

    public void setMutationOperator(MutationOperator mutationOperator) {
        this.mutationOperator = mutationOperator;
    }
}
