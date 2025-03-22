package com.ashwija.mvn.dao;

import com.ashwija.mvn.graphics.GraphicEngine;

import java.util.List;
import java.util.Map;

public abstract class Menu {
    private int padding;
    private String title;
    private String subTitle;
    private Map<Character, Menu> subMenu;
    private List<String> inputLabelList;
    private List<Object> inputList;

    public Menu(String title, int padding, Map<Character, Menu> subMenu, List<String> inputLabel) {
        this.title = title;
        this.padding = padding;
        this.subMenu = subMenu;
        this.inputLabelList = inputLabel;
    }

    public Menu(String title) {
        this.title = title;
    }

    public Map<Character, Menu> getSubMenu() {
        return subMenu;
    }

    public int inputLabelListSize() {
        return inputLabelList != null ? inputLabelList.size() : 0;

    }

    public Menu getSubMenuAt(Character character) {
        return subMenu.get(character);
    }

    @Override
    public String toString() {
        StringBuffer finalFormatedMenu = new StringBuffer();
        finalFormatedMenu.append(GraphicEngine.printHeaderBlock(this.padding, this.title));
        // TODO reset of the append logic here
        if (subMenu != null) {
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

    public String getInputLabelAt(int index) {
        return inputLabelList.get(index);
    }

    public void setInputLabelList(List<String> inputLabelList) {
        this.inputLabelList = inputLabelList;
    }

    public abstract void performAction(List<Object> inputList);
}
