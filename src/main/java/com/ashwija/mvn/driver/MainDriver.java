package com.ashwija.mvn.driver;

import com.ashwija.mvn.common.AppConstants;
import com.ashwija.mvn.entity.Menu;

import java.util.*;

public class MainDriver {
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        // Will get replaced with metadata fetch logic in future
        Menu mainMenu = AppConstants.getMainMenu();
        System.out.println(mainMenu);
        System.out.print(mainMenu.getInputLabel());

        char firstInput = scanner.next().charAt(0);
        Map<Character,Menu>subMenu=mainMenu.getSubMenu();

        if(subMenu.containsKey(firstInput)){
            Menu subMenuItem=subMenu.get(firstInput);
            System.out.println(subMenuItem);
            System.out.println(subMenuItem.getInputLabel());
            char secondInput = scanner.next().charAt(0);
        }
        else{
            System.out.println(mainMenu.getInputLabel());
            firstInput = scanner.next().charAt(0);
        }


    }


}
