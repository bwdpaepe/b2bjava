package service;

import domein.Bedrijf;
import domein.Transportdienst;
import service.BedrijfService;


public class BestellingService {
	
	BedrijfService bedrijfService = new BedrijfService();
	DienstService dienstService = new DienstService();
	

	public BestellingService() {
		// TODO Auto-generated constructor stub
	}
	
	public void maakBestelling(long orderID, String Status, int bedrijfID, int transportdienstID) {
		Bedrijf bedrijf = bedrijfService.getBedrijfById(bedrijfID);
		Transportdienst td = dienstService.getDienst(transportdienstID);
		
	}
	
	

}
