package com.ashwija.mvn.driver;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.dao.Menu;

import java.util.*;

public class MainDriver {
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        List<Object> inputList;

        while (true) {
            // Will get replaced with metadata fetch logic in future
            Menu currentMenu = CentralContext.getCurrentMenu();
            System.out.println(currentMenu);

            // perform in loop
            int labelIndex = 0;
            int labelIndexLimit = currentMenu.inputLabelListSize();

            inputList = new ArrayList<>();

            while (labelIndex < labelIndexLimit) {
                System.out.print(currentMenu.getInputLabelAt(labelIndex++));
                String input = scanner.next();
                //any point we type exit
                if (input.equalsIgnoreCase("EXIT")) {
                    System.exit(0);
                }
                //any point we type main reset to main menu
                if (input.equalsIgnoreCase("MAIN")) {
                    CentralContext.resetToMainMenu();
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
