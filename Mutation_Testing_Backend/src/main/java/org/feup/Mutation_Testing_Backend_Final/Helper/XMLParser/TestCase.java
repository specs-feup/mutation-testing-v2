package org.feup.Mutation_Testing_Backend_Final.Helper.XMLParser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class TestCase {
    private String name;
    private String className;
    private double time;
    private String status;
    private String failureMessage;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "classname")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlAttribute()
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @XmlElement(name = "failure")
    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }
}
