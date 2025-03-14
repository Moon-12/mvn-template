package com.ashwija.mvn.entity;

import com.ashwija.mvn.graphics.GraphicEngine;

import java.util.List;

public class Menu {
    private int padding;
    private String title;
    private String subTitle;
    private List<Menu> subMenu;
    private String inputLabel;

    @Override
    public String toString(){
        StringBuffer finalFormatedMenu = new StringBuffer();
        finalFormatedMenu.append(GraphicEngine.printHeaderBlock(this.padding));
        // TODO reset of the append logic here
        return finalFormatedMenu.toString();
    }

    public String getInputLabel() {
        return inputLabel;
    }
}
