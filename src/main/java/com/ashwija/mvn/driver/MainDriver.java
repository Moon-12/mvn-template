package com.ashwija.mvn.driver;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.menu.Menu;

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
                if (CentralContext.skipNextInput) {
                    CentralContext.skipNextInput = false;
                    labelIndex++;
                    continue;
                }
                String currentInputLabel = currentMenu.getInputLabelAt(labelIndex++);
                System.out.print(currentInputLabel);
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

                //if input requires dual confirmation
                if (currentInputLabel.startsWith("Do you want to")) {
                    //If confirmed then we will skip next input
                    if (input.equalsIgnoreCase("N")) {
                        CentralContext.skipNextInput = true;
                        // don't add  confirmation input to inputList when N
                        continue;
                    }

                }
                inputList.add(input);
            }

            if (comeOut) {
                break;
            }
            //reset to false if it's true
            if (CentralContext.skipNextInput) {
                CentralContext.skipNextInput = false;
            }
            currentMenu.performAction(inputList);

            // call underlying operations

//            if navigation go inside

            // if operation perform operation and move back in menu unless it's main menu
        }

    }

}
