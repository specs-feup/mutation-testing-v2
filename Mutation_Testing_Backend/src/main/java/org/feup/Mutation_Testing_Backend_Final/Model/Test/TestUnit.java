package org.feup.Mutation_Testing_Backend_Final.Model.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="TestUnit")
public class TestUnit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean failed;

    private Float testRunTime;

    private boolean skiped;

    private String unitTestName;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "testClass_id")
    private TestClass testClass;

    public TestUnit() {
    }

    public TestUnit(boolean failed, Float testRunTime, boolean skiped, String unitTestName) {
        this.failed = failed;
        this.testRunTime = testRunTime;
        this.skiped = skiped;
        this.unitTestName = unitTestName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public Float getTestRunTime() {
        return testRunTime;
    }

    public void setTestRunTime(Float testRunTime) {
        this.testRunTime = testRunTime;
    }

    public TestClass getTestClass() {
        return testClass;
    }

    public void setTestClass(TestClass testClass) {
        this.testClass = testClass;
    }

    public boolean isSkiped() {
        return skiped;
    }

    public void setSkiped(boolean skiped) {
        this.skiped = skiped;
    }

    public String getUnitTestName() {
        return unitTestName;
    }

    public void setUnitTestName(String unitTestName) {
        this.unitTestName = unitTestName;
    }

    @Override
    public String toString() {
        return "TestUnit{" +
                "id=" + id +
                ", failed=" + failed +
                ", testRunTime=" + testRunTime +
                ", skiped=" + skiped +
                ", unitTestName='" + unitTestName + '\'' +
                ", testClass=" + testClass +
                '}';
    }
}
