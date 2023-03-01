package service;

import domein.Dienst;
import domein.Transportdienst;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DienstService
{
	private GenericDao<Dienst> dienstRepo;

	public DienstService()
	{
		this.dienstRepo = new GenericDaoJpa<>(null);
	}
	
	public DienstService(GenericDao<Dienst> dienstRepo) {
		this.dienstRepo = dienstRepo;
	}

	public void maakTransportdienst(String naam, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatiecode, String contactVoornaam, String contactFamilienaam, String contactTelefoon,
			String contactEmailadres)
	{
		GenericDaoJpa.startTransaction();
		try {
		
			// maak de transportdienst
			Dienst dienst = new Transportdienst(naam);
			dienstRepo.insert(dienst);
			

			// TODO: maken van nieuwe TracTraceFormat en nieuwe ContactPerson, om te linken aan dienst, en vervolgens updaten
			// Services om TTF en CP nog aanmaken
			
//			dienst.setTractTraceFormaat(ttf);
//			
//			dienst.addPerson(contact);
//			
//			dienstRepo.update(dienst);
			
			
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
		}
		
		GenericDaoJpa.commitTransaction();
		
		
	}
	
	

}
