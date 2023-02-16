package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите строку: ");
            String request = scanner.next();
            if (request.equals("!quit")){
                break;
            }
            FindSystem.find(request, 2);
        }
    }
}