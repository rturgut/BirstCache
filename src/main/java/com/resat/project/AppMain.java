package com.resat.project;

import java.util.Scanner;

public class AppMain {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            Controller c = new Controller();
            System.out.println("Welcome to BirstCache...");
            System.out.println("Enter a command ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("QUIT")) {
                    System.out.println("Goodbye ...");
                    System.exit(0);
                    scanner.close();
                }
                c.executeCommand(line);
            }
        }
    }
}


