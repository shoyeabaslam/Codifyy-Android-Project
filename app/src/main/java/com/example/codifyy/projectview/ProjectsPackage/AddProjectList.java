package com.example.codifyy.projectview.ProjectsPackage;

public class AddProjectList {
    String child1,child2;

    public void setChild1(String child1) {
        this.child1 = child1;
    }

    public void setChild2(String child2) {
        this.child2 = child2;
    }

    public String getChild1() {
        return child1;
    }

    public String getChild2() {
        return child2;
    }

    public AddProjectList(String child1) {
        this.child1 = child1;
    }

    public AddProjectList(String child1, String child2) {
        this.child1 = child1;
        this.child2 = child2;
    }
}
