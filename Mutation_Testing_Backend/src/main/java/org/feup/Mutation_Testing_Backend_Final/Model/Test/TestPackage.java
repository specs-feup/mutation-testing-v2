package org.feup.Mutation_Testing_Backend_Final.Model.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TestPackage")
public class TestPackage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String packageName;

    @OneToMany(mappedBy = "testPackage")
    private List<TestClass> testClass;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "testPackage_id")
    private TestPackage testPackage;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "projectTestExecution_id")
    private ProjectTestExecution projectTestExecution;

    public TestPackage() {
    }

    public TestPackage(String packageName, ProjectTestExecution projectTestExecution) {
        this.packageName = packageName;
        this.projectTestExecution = projectTestExecution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<TestClass> getTestClass() {
        return testClass;
    }

    public void setTestClass(List<TestClass> testClass) {
        this.testClass = testClass;
    }

    public TestPackage getTestPackage() {
        return testPackage;
    }

    public void setTestPackage(TestPackage testPackage) {
        this.testPackage = testPackage;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ProjectTestExecution getProjectTestExecution() {
        return projectTestExecution;
    }

    public void setProjectTestExecution(ProjectTestExecution projectTestExecution) {
        this.projectTestExecution = projectTestExecution;
    }
}
