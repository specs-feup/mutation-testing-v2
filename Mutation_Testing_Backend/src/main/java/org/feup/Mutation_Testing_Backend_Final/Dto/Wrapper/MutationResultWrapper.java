package org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper;

import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectMutantGeneration;

import java.util.HashMap;

public class MutationResultWrapper {
    private ProjectMutantGeneration projectMutantGeneration;
    private int totalQuantityOfMutants;
    private float totalKadabraTime;
    private HashMap<String, Integer> mutantGeneration;



    public ProjectMutantGeneration getProjectMutantGeneration() {
        return projectMutantGeneration;
    }

    public void setProjectMutantGeneration(ProjectMutantGeneration projectMutantGeneration) {
        this.projectMutantGeneration = projectMutantGeneration;
    }

    public int getTotalQuantityOfMutants() {
        return totalQuantityOfMutants;
    }

    public void setTotalQuantityOfMutants(int totalQuantityOfMutants) {
        this.totalQuantityOfMutants = totalQuantityOfMutants;
    }

    public float getTotalKadabraTime() {
        return totalKadabraTime;
    }

    public void setTotalKadabraTime(float totalKadabraTime) {
        this.totalKadabraTime = totalKadabraTime;
    }

    public HashMap<String, Integer> getMutantGeneration() {
        return mutantGeneration;
    }

    public void setMutantGeneration(HashMap<String, Integer> mutantGeneration) {
        this.mutantGeneration = mutantGeneration;
    }
}


