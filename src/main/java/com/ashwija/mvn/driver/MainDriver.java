package com.ashwija.mvn.driver;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.AppConstants;
import com.ashwija.mvn.entity.Menu;

import java.util.*;

public class MainDriver {
    public void execute() {
        DatabaseConnection db = new DatabaseConnection();
        Scanner scanner = new Scanner(System.in);
        // Will get replaced with metadata fetch logic in future
        Menu currentMenu = AppConstants.getMainMenu();
        CentralContext.setCurrentMenu(currentMenu);
        while (true) {
            currentMenu = CentralContext.getCurrentMenu();
            System.out.println(currentMenu);

            // perform in loop
            int labelIndex = 0;
            int labelIndexLimit = currentMenu.inputLabelListSize();

            List<Object> inputList = new ArrayList<>();

            while (labelIndex < labelIndexLimit) {
                System.out.print(currentMenu.getInputLabelAt(labelIndex++));
                String input = scanner.next();
                if (input.equalsIgnoreCase("EXIT")) {
                    System.exit(0);
                }
                if (input.equalsIgnoreCase("MAIN")) {
                    CentralContext.setCurrentMenu(AppConstants.getMainMenu());
                    CentralContext.setPrevMenuMenu(null);
                }
                inputList.add(input);

            }

            currentMenu.performAction(inputList);

            // call underlying operations

//            if navigation go inside

            // if operation perform operation and move back in menu unless it's main menu
        }

    }

}
