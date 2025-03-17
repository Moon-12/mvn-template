package com.ashwija.mvn.driver;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.AppConstants;
import com.ashwija.mvn.entity.Menu;

import java.util.*;

public class MainDriver {
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        // Will get replaced with metadata fetch logic in future
        CentralContext.setCurrentMenu(AppConstants.getMainMenu());
        while (true){
            System.out.println(currentMenu);

            // perform in loop
            int labelIndex = 0;
            System.out.print(currentMenu.getInputLabelAt(labelIndex));
            List<String> inputList = new ArrayList<>();
            while(scanner.hasNext()){
                inputList.add(scanner.next());
                System.out.print(currentMenu.getInputLabelAt(++labelIndex));
            }

            currentMenu.performAction(inputList);

            // call underlying operations

//            if navigation go inside

            // if operation perform operation and move back in menu unless it's main menu
        }

    }
//        Map<Character,Menu>subMenu=mainMenu.getSubMenu();
//
//        if(subMenu.containsKey(firstInput)){
//            Menu subMenuItem=subMenu.get(firstInput);
//            System.out.println(subMenuItem);
//            System.out.println(subMenuItem.getInputLabel());
//            char secondInput = scanner.next().charAt(0);
//        }
//        else{
//            System.out.println(mainMenu.getInputLabel());
//            firstInput = scanner.next().charAt(0);
//        }
//    }


}
