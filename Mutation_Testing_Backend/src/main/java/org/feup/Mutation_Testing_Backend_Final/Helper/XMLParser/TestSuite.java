package org.feup.Mutation_Testing_Backend_Final.Helper.XMLParser;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "testsuite")
public class TestSuite {
    private int tests;
    private int failures;
    private int errors;
    private float time;
    private String name;
    private TestCase[] testCases;

    @XmlAttribute
    public int getTests() {
        return tests;
    }

    public void setTests(int tests) {
        this.tests = tests;
    }

    @XmlAttribute
    public int getFailures() {
        return failures;
    }

    public void setFailures(int failures) {
        this.failures = failures;
    }

    @XmlAttribute
    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    @XmlAttribute
    public float getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "testcase")
    public TestCase[] getTestCases() {
        return testCases;
    }

    public void setTestCases(TestCase[] testCases) {
        this.testCases = testCases;
    }

    @Override
    public String toString() {
        return "TestSuite{" +
                "tests=" + tests +
                ", failures=" + failures +
                ", errors=" + errors +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", testCases=" + Arrays.toString(testCases) +
                '}';
    }
}

