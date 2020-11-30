package com.victorp.service.impl;

import com.victorp.service.ChoiceService;
import com.victorp.service.MenuService;
import com.victorp.service.UserService;

import java.util.Scanner;

public class MenuServiceImpl implements MenuService {
    private static MenuService instance;

    public static MenuService getInstance() {

        if (instance == null) {
            synchronized (ChoiceService.class) {
                if (instance == null) {
                    instance = new MenuServiceImpl();
                }
            }
        }
        return instance;
    }

    private MenuServiceImpl() {
    }


    private final UserService userService = UserServiceImpl.getInstance();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void printOptions() {
        String menuText = "Available operations:\n" +
                "1 - Create User;\n" +
                "2 - Edit User;\n" +
                "3 - Delete User;\n" +
                "4 - Show User;\n" +
                "5 - Show All Users;\n" +
                "6 - Close application.";
        System.out.println(menuText);
    }

    @Override
    public int makeChoice() {
        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice > 6 || choice < 1) {
                System.out.println("There is no such option. Repeat entry.\n");
                printOptions();
            } else {
                return choice;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.\n");
            printOptions();
        }
        return -1;
    }

    @Override
    public boolean processingOperation(int choice) {
        switch (choice){
            case 1:
                try {
                    userService.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case 2:
                System.out.println("Enter last name: ");
                String strName = scanner.nextLine();
                try {
                    userService.edit(strName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Enter last name: ");
                String lastName = scanner.nextLine();
                System.out.println("do you really want to delete " + lastName + " ?");
                System.out.println("Yes-1/No-2");
                int c = 0;
                while (c == 0) {
                    try {
                        c = Integer.parseInt(scanner.nextLine());
                        if (c > 2 || c < 1) {
                            System.out.println("You must choice 1-Yes/2-No. Repeat entry.\n");
                            System.out.println("do you really want to delete " + lastName + " ?");
                            System.out.println("Yes-1/No-2");
                            choice = 0;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Try again.\n");
                    }
                }
                switch (c) {
                    case 1:
                        try {
                            userService.delete(lastName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:

                        break;
                }

                break;
            case 4:
                System.out.println("Enter LastName user: ");
                String str = scanner.nextLine();
                try {
                    userService.view(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case 5:
               try{
                   userService.viewAll();
               }catch (Exception e){
                   System.out.println("Exception ");
               }

                break;
            case 6:
                System.out.println("The work is completed.\nGoodbye");
                return true;
        }
        return false;
    }
}
