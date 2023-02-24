package main;

import domein.DomeinController;
import gui.ConsoleUi;

//TESTKLASSE STARTUP

public class StartUp {
    public static void main(String [] arg) {
        new StartUp().run();
    }

    private void run() {
        new ConsoleUi(new DomeinController()).run();
        //new ConsoleUi(new DomeinController(false)).run(); //zonder DB populate
    }
    
}
