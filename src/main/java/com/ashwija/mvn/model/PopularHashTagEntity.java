package com.ashwija.mvn.model;

import java.util.ArrayList;
import java.util.List;

public class PopularHashTagEntity extends AppEntity {
    private String name;
    private final Integer frequency;
    final static List<String> headers = new ArrayList<>();

    static {
        headers.addAll(List.of("HashTag", "Frequency"));
    }

    public PopularHashTagEntity(String name, Integer frequency) {
        this.name = name;
        this.frequency = frequency;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%-12s| %-12d", this.name, this.frequency);
    }

    @Override
    public String getHeader() {
        return formatHeader(headers);
    }
}
