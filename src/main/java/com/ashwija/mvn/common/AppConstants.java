package com.ashwija.mvn.common;

import com.ashwija.mvn.dao.*;

import java.util.*;

public class AppConstants {
    private static Menu mainMenu;
    private static List<String> mainMenuLabels = new ArrayList<>();
    private static List<String> studentMenuLabels = new ArrayList<>();
    private static List<String> addStudentLabels = new ArrayList<>(Arrays.asList(
            "Enter Roll No:",
            "Enter Name:"));

    static {
        Map<Character, Menu> studentMenu = getStudentMenu();
        Map<Character, Menu> mainMenuItems = Map.of(
                '1', new NavigationMenu("Student Operations", 1, studentMenu, studentMenuLabels),
                '2', new NavigationMenu("Teacher Operations"),
                '3', new NavigationMenu("Course Operations"),
                '4', new NavigationMenu("Course Assignment (One Teacher, Many Students) & Marks"));

        mainMenuLabels.add("Please enter your choice (1-" + mainMenuItems.size() + "): ");
        mainMenu = new NavigationMenu("Welcome to the School Management System!", 0, mainMenuItems, mainMenuLabels);
    }

    private static Map<Character, Menu> getStudentMenu() {
        Map<Character, Menu> studentMenuItems = new HashMap<>();
        AppDao studentEntity = new StudentDao();
        Menu addStudentOprationMenu = new OperationMenu("Add a new student", studentEntity, OperationType.ADD);
        addStudentOprationMenu.setInputLabelList(addStudentLabels);
        studentMenuItems.put('a', addStudentOprationMenu);
        studentMenuItems.put('b', new OperationMenu("View student details", studentEntity, OperationType.VIEW));
        //studentMenuItems.put('c', new OperationMenu("Update student information", studentEntity));
        studentMenuItems.put('c', new OperationMenu("Delete a student", studentEntity, OperationType.DELETE));

        studentMenuLabels.add("Please enter your choice (a-" + (char) ('a' + studentMenuItems.size() - 1) + "): ");
        return studentMenuItems;
    }

    public static Menu getMainMenu() {
        return mainMenu;
    }
}
