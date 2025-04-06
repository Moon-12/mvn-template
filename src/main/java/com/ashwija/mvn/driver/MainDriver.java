package com.ashwija.mvn.driver;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.dao.Menu;

import java.io.InputStream;
import java.util.*;

public class MainDriver {
    DatabaseConnection databaseConnection;
    public void execute(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\n");
        List<Object> inputList;

        while (true) {
            // Will get replaced with metadata fetch logic in future
            Menu currentMenu = CentralContext.peekCurrentMenuStack();
            System.out.println(currentMenu);

            // perform in loop
            int labelIndex = 0;
            int labelIndexLimit = currentMenu.inputLabelListSize();

            inputList = new ArrayList<>();

            boolean comeOut = false;
            while (labelIndex < labelIndexLimit) {
                System.out.print(currentMenu.getInputLabelAt(labelIndex++));
                String input = scanner.nextLine();
                //any point we type exit
                if (input.equalsIgnoreCase("EXIT")) {
                    comeOut = true;
                    break;
                }
                //any point we type main reset to main menu
                if (input.equalsIgnoreCase("MAIN")) {
                    CentralContext.logOut();
                }

                inputList.add(input);

            }

            if (comeOut) {
                break;
            }
            currentMenu.performAction(inputList);

            // call underlying operations

//            if navigation go inside

            // if operation perform operation and move back in menu unless it's main menu
        }

    }

}
