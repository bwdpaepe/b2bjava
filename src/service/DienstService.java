package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Bedrijf;
import domein.Contactpersoon;
import domein.Dienst;
import domein.Persoon;
import domein.TrackTraceFormat;
import domein.Transportdienst;
import repository.DienstDTO;
import repository.GenericDao;
import repository.GenericDaoJpa;
import repository.TransportdienstDTO;

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
			String verificatiecode, List<String> contactVoornaamLijst, List<String> contactFamilienaamLijst, List<String> contactTelefoonLijst,
			List<String> contactEmailadresLijst, Bedrijf bedrijf)
	{
		GenericDaoJpa.startTransaction();
		try {
		
			// maak de transportdienst
			Dienst dienst = new Transportdienst(naam, bedrijf);
			// new ttf
			TrackTraceFormat ttf = new TrackTraceFormat(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
					verificatiecode);
			dienst.setTrackTraceFormat(ttf);
			
			Iterator<String> voornaamIterator = contactVoornaamLijst.iterator();
			Iterator<String> familienaamIterator = contactFamilienaamLijst.iterator();
			Iterator<String> telefoonIterator = contactTelefoonLijst.iterator();
			Iterator<String> emailIterator = contactEmailadresLijst.iterator();
			
			// new contactpersoon
			while (voornaamIterator.hasNext() && familienaamIterator.hasNext() && telefoonIterator.hasNext() && emailIterator.hasNext()) {
				dienst.addPerson(new Contactpersoon(voornaamIterator.next(), familienaamIterator.next(), emailIterator.next(), telefoonIterator.next()));
			}
			
			dienstRepo.insert(dienst);			
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			System.err.println(e);
			GenericDaoJpa.rollbackTransaction();
		}
		

		
	}
	
	public void wijzigActivatieDienst(long dienstId, boolean isActief) throws IllegalArgumentException {
		try {
			Dienst d = this.dienstRepo.get(dienstId);
			if(d instanceof Transportdienst) {
				
				d.setActief(isActief);
				GenericDaoJpa.startTransaction();
				this.dienstRepo.update(d);
				GenericDaoJpa.commitTransaction();
				
			}
			else
			{
				throw new IllegalArgumentException("Ongeldig diensttype");
			}
		}
		catch(EntityNotFoundException ex) {
			throw new IllegalArgumentException("De gevraagde dienst bestaat niet");
		}
		
	}
	
	public DienstDTO getDienst(long dienstId) throws IllegalArgumentException {
		try {
			Dienst d = this.dienstRepo.get(dienstId);
			if(d instanceof Transportdienst) {
				
				List<String> voornaamLijst = new ArrayList<>();
				List<String> familienaamLijst = new ArrayList<>();
				List<String> emailAdressLijst = new ArrayList<>();
				List<String> telefoonnummerLijst = new ArrayList<>();
				for(Persoon p: d.getPersonen()) {
					voornaamLijst.add(p.getVoornaam());
					familienaamLijst.add(p.getFamilienaam());
					emailAdressLijst.add(p.getEmailAdress());
					telefoonnummerLijst.add(p.getTelefoonnummer());
				}
				
				return new TransportdienstDTO(d.getId(), d.getNaam(), d.isActief(), voornaamLijst, familienaamLijst, emailAdressLijst, telefoonnummerLijst, 
						((Transportdienst)d).getBarcodeLengte(), ((Transportdienst)d).isBarcodeEnkelCijfers(), ((Transportdienst)d).getBarcodePrefix(), ((Transportdienst)d).getVerificatieCode());
				
			}
			else
			{
				throw new IllegalArgumentException("Ongeldig diensttype");
			}
		}
		catch(EntityNotFoundException ex) {
			throw new IllegalArgumentException("De gevraagde dienst bestaat niet");
		}
		
	}
	
	// ToDo rewrite using streams
	public List<DienstDTO> getDiensten() {
		List<DienstDTO> dienstDTOList = new ArrayList<>();
		List<Dienst> dienstList = getDienstList();
		for(Dienst d: dienstList) {
			List<String> voornaamLijst = new ArrayList<>();
			List<String> familienaamLijst = new ArrayList<>();
			List<String> emailAdressLijst = new ArrayList<>();
			List<String> telefoonnummerLijst = new ArrayList<>();
			for(Persoon p: d.getPersonen()) {
				voornaamLijst.add(p.getVoornaam());
				familienaamLijst.add(p.getFamilienaam());
				emailAdressLijst.add(p.getEmailAdress());
				telefoonnummerLijst.add(p.getTelefoonnummer());
			}
			
			dienstDTOList.add(new TransportdienstDTO(d.getId(), d.getNaam(), d.isActief(), voornaamLijst, familienaamLijst, emailAdressLijst, telefoonnummerLijst, 
					((Transportdienst)d).getBarcodeLengte(), ((Transportdienst)d).isBarcodeEnkelCijfers(), ((Transportdienst)d).getBarcodePrefix(), ((Transportdienst)d).getVerificatieCode()));
			
			
		}
		
		return dienstDTOList;
		
		
		/*return getDienstList().stream()
							  .map(d -> new TransportdienstDTO(d.getNaam(), d.isActief(), 
									  d.getPersonen().stream().map(p -> p.getEmailAdress())
									  .collect(Collectors.toList()), 
									  new TrackTraceFormatDTO(((Transportdienst)d).getTrackTraceFormaat().getBarcodeLengte(),((Transportdienst)d).getTrackTraceFormaat().isBarcodeEnkelCijfers(),((Transportdienst)d).getTrackTraceFormaat().getBarcodePrefix(),((Transportdienst)d).getTrackTraceFormaat().getVerificatieCode())))
							  .collect(Collectors.toUnmodifiableList());*/
	}
	//.map((Persoon)p -> new ContactpersoonDTO(((Contactpersoon)p).getVoornaam(), ((Contactpersoon)p).getFamilienaam(), ((Contactpersoon)p).getEmailAdress(), ((Contactpersoon)p).getTelefoonnummer()))
	
	private List<Dienst> getDienstList(){
		List<Dienst> dienstList=dienstRepo.findAll();
        return dienstList;
    }
	
	

}
