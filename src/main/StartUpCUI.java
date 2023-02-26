package main;

import domein.DomeinController;
import gui.ConsoleUi;

//TESTKLASSE STARTUP

public class StartUpCUI {
    public static void main(String [] arg) {
        new StartUpCUI().run();
    }

    private void run() {
        new ConsoleUi(new DomeinController()).run();
        
    }
    
}
