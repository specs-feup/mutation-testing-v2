package org.feup.Mutation_Testing_Backend_Final.Model.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TestClass")
public class TestClass {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "testClass")
    private List<TestUnit> testUnitList;

    private String className;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "testPackage_id")
    private TestPackage testPackage;

    public TestClass() {
    }


    public TestClass(String className, TestPackage testPackage) {
        this.className = className;
        this.testPackage = testPackage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TestUnit> getTestUnitList() {
        return testUnitList;
    }

    public void setTestUnitList(List<TestUnit> testUnitList) {
        this.testUnitList = testUnitList;
    }

    public TestPackage getTestPackage() {
        return testPackage;
    }

    public void setTestPackage(TestPackage testPackage) {
        this.testPackage = testPackage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
