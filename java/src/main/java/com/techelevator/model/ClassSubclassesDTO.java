package com.techelevator.model;

import java.util.List;

public class ClassSubclassesDTO {
    private String className;
    private List<String> subclasses;

    public ClassSubclassesDTO() {}

    public ClassSubclassesDTO(String className, List<String> subclasses) {
        this.className = className;
        this.subclasses = subclasses;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<String> subclasses) {
        this.subclasses = subclasses;
    }
}
