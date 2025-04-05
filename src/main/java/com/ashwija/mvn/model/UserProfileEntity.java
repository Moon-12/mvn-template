package com.ashwija.mvn.model;

public class UserProfileEntity extends AppEntity {
    private String id;
    private String password;
    private String gender;
    private String school;

    public UserProfileEntity(String id, String password, String gender, String school) {
        this.id = id;
        this.password = password;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String getHeader() {
        return "";
    }
}
