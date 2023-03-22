package main;

import domein.DomeinController;

public class StartUpCUI {
    public static void main(String [] arg) {
        new StartUpCUI().run();
    }

    private void run() {
    	DomeinController domeinController = new DomeinController(true);  // boolean om al dan niet database te seeden
    	
		//NON-seed related operations
    	//--------------------------------------------------------------------------------------------------------------

		
		domeinController.wijzigdoos(1, "nieuwe doos", 50f, 75f, 80f, "standaard", 300, true);
		domeinController.wijzigdoos(1, "nieuwe doos", 50f, 75f, 80f, "standaard", 300, false);
		
		domeinController.aanmelden("mag1@test.com", "paswoord");
		try {
			domeinController.wijzigdoos(1, "nieuwe doos", 50f, 70f, 80f, "standaard", 300, true);
		} catch (Error e) {
			System.out.println(e.getMessage());
		}

		
    	domeinController.close();
    }
}
