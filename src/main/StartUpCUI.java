package main;

import domein.DomeinController;

public class StartUpCUI {
    public static void main(String [] arg) {
        new StartUpCUI().run();
    }

    private void run() {
    	DomeinController domeinController = new DomeinController(true);  // boolean om al dan niet database te seeden
    	domeinController.close();
    }
}
