package com.ashwija.mvn.entity;

import com.ashwija.mvn.graphics.GraphicEngine;

import java.util.Map;

public class Menu {
    private int padding;
    private String title;
    private String subTitle;
    private Map<Character,Menu> subMenu;
    private String inputLabel;

    public Menu(String title,int padding,Map<Character,Menu> subMenu,String inputLabel) {
        this.title = title;
        this.padding=padding;
        this.subMenu=subMenu;
        this.inputLabel=inputLabel;
    }

    public  Menu(String title){
        this.title=title;
    }

    public Map<Character, Menu> getSubMenu() {
        return subMenu;
    }

    @Override
    public String toString(){
        StringBuffer finalFormatedMenu = new StringBuffer();
        finalFormatedMenu.append(GraphicEngine.printHeaderBlock(this.padding, this.title));
        // TODO reset of the append logic here
        if(subMenu!=null) {
            finalFormatedMenu.append(printSubMenu());
        }
        return finalFormatedMenu.toString();
    }

    private String printSubMenu() {
        StringBuilder subMenuText = new StringBuilder();
        if (subMenu != null) {
                for (Map.Entry<Character, Menu> entry : subMenu.entrySet()) {
                    subMenuText.append(entry.getKey())
                            .append(". ")
                            .append(entry.getValue().title)
                            .append("\n");
                }
        }
        return subMenuText.toString();
    }

    public String getInputLabel() {
        return inputLabel;
    }
}
