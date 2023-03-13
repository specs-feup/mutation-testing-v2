package org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper;

public class ProjectClassMutantSchemataWrapper extends ProjectClassWrapper{
    private String mutantSchemata;

    public String getMutantSchemata() {
        return mutantSchemata;
    }

    public void setMutantSchemata(String mutantSchemata) {
        this.mutantSchemata = mutantSchemata;
    }
}
