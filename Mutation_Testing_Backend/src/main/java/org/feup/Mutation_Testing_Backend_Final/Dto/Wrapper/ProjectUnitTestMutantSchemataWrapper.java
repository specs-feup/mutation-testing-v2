package org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper;

public class ProjectUnitTestMutantSchemataWrapper extends ProjectUnitTestWrapper{
    private String mutantSchemata;

    public String getMutantSchemata() {
        return mutantSchemata;
    }

    public void setMutantSchemata(String mutantSchemata) {
        this.mutantSchemata = mutantSchemata;
    }
}
