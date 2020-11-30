package com.victorp.service.impl;

import com.victorp.service.ChoiceService;

import java.util.Scanner;

public class ChoiceServiceImpl implements ChoiceService {
    private static ChoiceService instance;

    public static ChoiceService getInstance() {

        if (instance == null) {
            synchronized (ChoiceService.class) {
                if (instance == null) {
                    instance = new ChoiceServiceImpl();
                }
            }
        }
        return instance;
    }

    private ChoiceServiceImpl() {
    }


    @Override
    public int choiceYes_NO(int choice) throws Exception {
        Scanner in = new Scanner(System.in);
        while (choice == 0) {
            try {
                choice = Integer.parseInt(in.nextLine());
                if (choice > 2 || choice < 1) {
                    System.out.println("You must choice 1-Yes/2-No. Repeat entry.\n");
                    System.out.println("Enter another role? ");
                    System.out.println("Yes-1/No-2");
                    choice = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.\n");
            }
        }
        return choice;
    }

    @Override
    public int choiceEdit_Add(int choice) throws Exception {
        Scanner in = new Scanner(System.in);
        while (choice == 0) {
            try {
                choice = Integer.parseInt(in.nextLine());
                if (choice > 2 || choice < 1) {
                    System.out.println("You must choice 1-edit/2-add. Repeat entry.\n");
                    System.out.println("You want to edit or add phone number? ");
                    choice = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.\n");
            }
        }
        return choice;
    }

    @Override
    public int choiceNumberToReplace(int positionOfPhoneNumber, int count) throws Exception {
        Scanner in = new Scanner(System.in);
        while (positionOfPhoneNumber == -1) {
            try {
                positionOfPhoneNumber = Integer.parseInt(in.nextLine());
                if (positionOfPhoneNumber > count || positionOfPhoneNumber < 0) {
                    System.out.println("You must select the number that is in the list. Repeat entry.\n");
                    System.out.println("select the phone number to replace");
                    positionOfPhoneNumber = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.\n");
            }
        }
        return positionOfPhoneNumber;
    }

    @Override
    public int choiceRoleToReplace(int positionOfRole, int count) throws Exception {
        Scanner in = new Scanner(System.in);
        while (positionOfRole == -1) {
            try {
                positionOfRole = Integer.parseInt(in.nextLine());
                if (positionOfRole > count || positionOfRole < 0) {
                    System.out.println("You must select the number that is in the list. Repeat entry.\n");
                    System.out.println("select the role to replace");
                    positionOfRole = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.\n");
            }
        }
        return positionOfRole;
    }
}
