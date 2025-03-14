package com.ashwija.mvn.driver;

import com.ashwija.mvn.entity.Menu;

import java.util.Scanner;

public class MainDriver {
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        Menu mainMenu = new Menu();
        System.out.println(mainMenu);
        System.out.println(mainMenu.getInputLabel());
        int firstInput = scanner.nextInt();
    }
}
