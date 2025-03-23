package com.ashwija.mvn.common;

import com.ashwija.mvn.MavenTemplateMain;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Utility {
    public static InputStream resourceToInputStream(String fileName) {
        InputStream input = Utility.class.getClassLoader().getResourceAsStream(fileName);
        // Check if the file is found
        if (input == null) {
            System.out.println("error converting to stream");
        }
        return input;
    }

    public static Map<String, Object> yamlToMap(String fileName) {
        Map<String, Object> mapping = Map.of();
        try {
            InputStream input = resourceToInputStream(fileName);
            Yaml yaml = new Yaml();
            // Parse YAML into a nested Map
            mapping = yaml.load(input);
        } catch (Exception e) {
            System.out.println("Error reading " + fileName + " file!");
            e.printStackTrace();
        }
        return mapping;
    }
}