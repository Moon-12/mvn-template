package com.ashwija.mvn;

import java.util.Scanner;

public class MavenTemplateMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println("Sum is "+sum(x,y));
    }

    public static int sum(int x, int y){
        return x+y;
    }
}
