package com.ashwija.mvn.entity;

import java.util.List;

public interface AppEntity {
    public void save(AppEntity myEntity);

    public void delete(AppEntity myEntity);

    public AppEntity view(AppEntity myEntity);

    public AppEntity performObjectFactoryFromList(List<Object> inputs);
}
