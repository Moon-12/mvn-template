package com.ashwija.mvn.driver;

import com.ashwija.mvn.entity.Menu;

import java.util.*;

public class MainDriver {
    public void execute(){
        List<Map<Character, Menu>> mainMenuList=new ArrayList<>();
        Map<Character, Menu> mainMenuItems = new HashMap<>();
//        mainMenuItems.put('1', new Menu("Student Operations"));
//        mainMenuItems.put('2', new Menu("Teacher Operations"));
//        mainMenuItems.put('3', new Menu("Course Operations"));
//        mainMenuItems.put('4', new Menu("Course Assignment (One Teacher, Many Students) & Marks"));
//        mainMenuItems.put('5', new Menu("Exit"));
        mainMenuList.add(mainMenuItems);

        Scanner scanner = new Scanner(System.in);
        Menu mainMenu = new Menu("Welcome to the School Management System!",0);
        System.out.println(mainMenu);
        System.out.println(mainMenu.getInputLabel());
        int firstInput = scanner.nextInt();
    }
}
