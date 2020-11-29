package com.victorp.main;

import com.victorp.view.ConsolMenu;

public class Main {

    public static void main(String[] args) {


        ConsolMenu consolMenu = new ConsolMenu();
        try {
            consolMenu.startConsoleMenu();
        } catch (Exception e) {

        }
    }
}
