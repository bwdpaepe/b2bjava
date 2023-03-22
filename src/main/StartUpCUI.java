package main;



import domein.DomeinController;


public class StartUpCUI {
	public static void main(String[] arg) {
		new StartUpCUI().run();
	}

	private void run() {
		DomeinController domeinController = new DomeinController(true); // boolean om al dan niet database te seeden

		// NON-seed related operations
		// --------------------------------------------------------------------------------------------------------------

		domeinController.wijzigdoos(1, "nieuwe doos", 50f, 75f, 80f, "standaard", 300, true);
		domeinController.wijzigdoos(1, "nieuwe doos", 50f, 75f, 80f, "standaard", 300, false);

		try {
			domeinController.maakDoos(null, "blah1", 1, 2, 30, 4);
		} catch (Exception e) {
			System.out.println(e.getMessage() + e.toString());
			
		}
		try {
			domeinController.maakDoos("", "blah2", 1, 2, 30, 4);
		} catch (Exception e) {
			System.out.println(e.getMessage() + e.toString());		}
		try {
			domeinController.maakDoos("haha", "blah3", 1, 2, 30, 4);

		} catch (Exception e) {
			System.out.println(e.getMessage()+ e.toString());		}

		domeinController.close();
	}
}
