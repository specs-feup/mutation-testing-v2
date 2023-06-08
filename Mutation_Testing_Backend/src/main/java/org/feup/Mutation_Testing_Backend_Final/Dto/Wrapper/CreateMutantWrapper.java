package org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper;

import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperator;

import java.util.List;

public class CreateMutantWrapper {
    private List<MutationOperator> operatorList;
    private String classPath;
    private Boolean useIncompleteClassPath;
    private Boolean useGradleTaskClassPath;

    public CreateMutantWrapper(List<MutationOperator> operatorList, String classPath, Boolean useIncompleteClassPath, Boolean useGradleTaskClassPath) {
        this.operatorList = operatorList;
        this.classPath = classPath;
        this.useIncompleteClassPath = useIncompleteClassPath;
        this.useGradleTaskClassPath = useGradleTaskClassPath;
    }

    public List<MutationOperator> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(List<MutationOperator> operatorList) {
        this.operatorList = operatorList;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public Boolean getUseIncompleteClassPath() {
        return useIncompleteClassPath;
    }

    public void setUseIncompleteClassPath(Boolean useIncompleteClassPath) {
        this.useIncompleteClassPath = useIncompleteClassPath;
    }

    public Boolean getUseGradleTaskClassPath() {
        return useGradleTaskClassPath;
    }

    public void setUseGradleTaskClassPath(Boolean useGradleTaskClassPath) {
        this.useGradleTaskClassPath = useGradleTaskClassPath;
    }
}
