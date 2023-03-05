package service;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Transportdienst;
import repository.GenericDaoJpa;



public class BestellingService {
	
	BedrijfService bedrijfService = new BedrijfService();
	DienstService dienstService = new DienstService();
	GenericDaoJpa<Bestelling> = new GenericDAO();
	

	public BestellingService() {
		// TODO Auto-generated constructor stub
	}
	
	public void maakBestelling(long orderID, String Status, int bedrijfID, int transportdienstID) {
		Bedrijf bedrijf = bedrijfService.getBedrijfById(bedrijfID);
		Transportdienst td = dienstService.getTransportdienstByID(transportdienstID);
		
		GenericDaoJpa.startTransaction();
		
	}
	
	

}
