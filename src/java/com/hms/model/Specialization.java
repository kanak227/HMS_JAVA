package com.hms.model;

public class Specialization {
    private int id;
    private String name;

    public Specialization(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}
