package com.ashwija.mvn.model;

import java.util.ArrayList;
import java.util.List;

public class TeacherEntity extends AppEntity {
    private int id;
    private String name;
    final static List<String> headers = new ArrayList<>();

    static {
        List.of("ID", "Name").forEach(headers::add);
    }

    public TeacherEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%-12d| %-12s", this.id, this.name);
    }

    @Override
    public String getHeader() {
        return formatHeader(headers);
    }
}
