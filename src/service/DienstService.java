package service;

import domein.Dienst;
import domein.TrackTraceFormat;
import domein.Transportdienst;
import repository.GenericDao;
import repository.GenericDaoJpa;
import repository.TrackTraceFormatDoaJpa;

public class DienstService
{
	private GenericDao<Dienst> dienstRepo;

	public DienstService()
	{
		this.dienstRepo = new GenericDaoJpa<>(Dienst.class);
	}
	
	public DienstService(GenericDao<Dienst> dienstRepo) {
		this.dienstRepo = dienstRepo;
	}

	public void maakTransportdienst(String naam, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatiecode, String contactVoornaam, String contactFamilienaam, String contactTelefoon,
			String contactEmailadres, TrackTraceFormatService ttfService)
	{
		GenericDaoJpa.startTransaction();
		try {
		
			// maak de transportdienst
			Dienst dienst = new Transportdienst(naam);
			dienstRepo.insert(dienst);
			

			// TODO: maken van nieuwe TracTraceFormat en nieuwe ContactPerson, om te linken aan dienst, en vervolgens updaten
			// Services om TTF en CP nog aanmaken
			TrackTraceFormatDoaJpa.startTransaction();
			ttfService.maakTrackTraceFormat(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
					verificatiecode);
			TrackTraceFormat ttf = ttfService.findTtfByAll(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
					verificatiecode);
			TrackTraceFormatDoaJpa.commitTransaction();
			
			
			dienst.setTractTraceFormaat(ttf);
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
