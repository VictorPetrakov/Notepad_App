package com.victorp.view;

import com.victorp.service.MenuService;
import com.victorp.service.impl.MenuServiceImpl;

public class ConsolMenu {
    private MenuService consoleMenuAction = new MenuServiceImpl();

    public void startConsoleMenu() throws Exception {
        int choice;
        boolean isStopped = false;
        try {
            consoleMenuAction.printOptions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!isStopped) {
            System.out.println("Please enter the number of the operation. \nYour choice: ");
            choice = consoleMenuAction.makeChoice();
            isStopped = consoleMenuAction.processingOperation(choice);
        }
    }
}
