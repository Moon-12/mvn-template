package com.ashwija.mvn.common;

import com.ashwija.mvn.entity.Menu;

import java.util.*;

public class AppConstants {
    private static Menu mainMenu;
    private static String mainMenuLabel;
    private static String subMenuLabel;

    static{
        Map<Character, Menu> mainMenuItems = new HashMap<>();

        Map<Character,Menu> studentMenu=getStudentMenu();
        mainMenuItems.put('1', new Menu("Student Operations",1,studentMenu,subMenuLabel));
        mainMenuItems.put('2', new Menu("Teacher Operations"));
        mainMenuItems.put('3', new Menu("Course Operations"));
        mainMenuItems.put('4', new Menu("Course Assignment (One Teacher, Many Students) & Marks"));
        mainMenuItems.put('5', new Menu("Exit"));

        mainMenuLabel = "Please enter your choice (1-" + mainMenuItems.size() + "): ";

        mainMenu = new Menu("Welcome to the School Management System!",0,mainMenuItems,mainMenuLabel);
    }
    private static Map<Character,Menu>getStudentMenu(){
        Map<Character, Menu> studentMenuItems = new HashMap<>();
        studentMenuItems.put('a', new Menu("Add a new student"));
        studentMenuItems.put('b', new Menu("View student details"));
        studentMenuItems.put('c', new Menu("Update student information"));
        studentMenuItems.put('d', new Menu("Delete a student"));
        studentMenuItems.put('e', new Menu("Back to Main Menu"));
        subMenuLabel = "Please enter your choice (a-" + (char)('a' + studentMenuItems.size() - 1) + "): ";
        return  studentMenuItems;
    }
    public static Menu getMainMenu(){
        return mainMenu;
    }
}
