package com.ashwija.mvn.model;

import java.util.ArrayList;
import java.util.List;

public class SampleEntity extends AppEntity {
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
    private int id;
    final static List<String> headers = new ArrayList<>();

    static {
        List.of("ID", "Name", "Age").forEach(headers::add);
    }

    public int getId() {
        return id;
    }

    public SampleEntity(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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
    public String getHeader() {
        return formatHeader(headers);
    }

    @Override
    public String toString() {
        return String.format("%-12d| %-12s | %-12d ", this.id, this.name, this.age);

    }
}
