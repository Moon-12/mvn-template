package com.ashwija.mvn.model;

import java.util.ArrayList;
import java.util.List;

public class UserProfileEntity extends AppEntity {
    private String id;
    private String password;
    private final String gender;
    private String school;

    final static List<String> headers = new ArrayList<>();

    static {
        headers.addAll(List.of("User ID", "Gender", "School"));
    }

    public UserProfileEntity(String id, String gender, String school) {
        this.id = id;
        this.gender = gender;
        this.school = school;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String getHeader() {
        return formatHeader(headers);
    }

    @Override
    public String toString() {
        return String.format("%-12s| %-12s| %-12s", this.id, this.gender, this.school);
    }
}
