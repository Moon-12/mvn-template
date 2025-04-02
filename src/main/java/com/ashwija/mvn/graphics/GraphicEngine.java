package com.ashwija.mvn.graphics;

public class GraphicEngine {

    public static String printHeaderBlock(int paddingAboveBottom, String title){
        int totalLength=80;
        String border="*".repeat(totalLength)+"\n";
        boolean isOdd=title.length()%2!=0;
        int spacePerSide=(totalLength-title.length()-2)/2;
        String padding;

        padding="*" + " ".repeat(78)+ "*" + "\n";
        padding=padding.repeat(paddingAboveBottom);

        border+=padding+ "*"
                + " ".repeat(spacePerSide) + title + " ".repeat(isOdd?spacePerSide+1:spacePerSide)
                + "*" +"\n"
                + padding
                + border;
        return border;

    }
}
