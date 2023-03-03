package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;


import domein.Dienst;
import domein.Persoon;
import domein.TrackTraceFormat;
import domein.Transportdienst;
import repository.ContactpersoonDTO;
import repository.DienstDTO;
import repository.GenericDao;
import repository.GenericDaoJpa;
import repository.PersoonDTO;
import repository.TrackTraceFormatDTO;
import repository.TrackTraceFormatDoaJpa;
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
			List<String> contactEmailadresLijst, TrackTraceFormatService ttfService, PersoonService persoonService)
	{
		// maken van nieuwe TrackTraceFormat, om te linken aan dienst
		TrackTraceFormatDoaJpa.startTransaction();
		ttfService.maakTrackTraceFormat(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
				verificatiecode);
		TrackTraceFormatDoaJpa.commitTransaction();
		
		// maken van nieuwe ContactPersonen, om te linken aan dienst
		GenericDaoJpa.startTransaction();
		
		//ToDo: check all list have the same size
		//ToDo: use streams
		
		Iterator<String> voornaamIterator = contactVoornaamLijst.iterator();
		Iterator<String> familienaamIterator = contactFamilienaamLijst.iterator();
		Iterator<String> telefoonIterator = contactTelefoonLijst.iterator();
		Iterator<String> emailIterator = contactEmailadresLijst.iterator();
		
		while (voornaamIterator.hasNext() && familienaamIterator.hasNext() && telefoonIterator.hasNext() && emailIterator.hasNext()) {
			persoonService.maakPersoon(voornaamIterator.next(), familienaamIterator.next(), telefoonIterator.next(), emailIterator.next());
		}
		
		/*StreamUtils.zip(
				contactVoornaamLijst.stream(), 
				contactFamilienaamLijst.stream(),
				contactTelefoonLijst.stream(), 
				contactEmailadresLijst.stream(),
				  (String contactVoornaam, String contactFamilienaam, String contactTelefoon, String contactEmailadres) -> persoonService.maakPersoon(contactVoornaam, contactFamilienaam, contactTelefoon, contactEmailadres));*/
				
		GenericDaoJpa.commitTransaction();
					
		// maak de transportdienst
		// link ttf
		// link contact
		GenericDaoJpa.startTransaction();
		try {
		
			// maak de transportdienst
			Dienst dienst = new Transportdienst(naam);
			dienstRepo.insert(dienst);

			try {
				TrackTraceFormat ttf = ttfService.findTtfByAll(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
						verificatiecode);
				dienst.setTrackTraceFormat(ttf);
			}
			catch(EntityNotFoundException e) {
				throw e;
			}
			
			contactEmailadresLijst.forEach(email -> {
				Optional<Persoon> contact = persoonService.getPersoonList().stream()
		                .filter( p -> p.getEmailAdress().equalsIgnoreCase(email))
		                .findFirst();
		        if (!contact.isPresent()) {
		                throw new IllegalArgumentException("contactpersoon " + email + " komt niet voor");
		        }
				
				dienst.addPerson(contact.get());
			});
			
			
			dienstRepo.update(dienst);			
			
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
		}
		
		GenericDaoJpa.commitTransaction();
		
	}
	
	public void updateTransportdienst(long dienstId, String naam, boolean isActief, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatiecode, List<String> contactVoornaamLijst, List<String> contactFamilienaamLijst, List<String> contactTelefoonLijst,
			List<String> contactEmailadresLijst, TrackTraceFormatService ttfService, PersoonService persoonService) throws IllegalArgumentException
	{
		// get TrackTraceFormat and update
		ttfService.updateTrackTraceFormat( barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
				verificatiecode);
		
		
		// get all Contactpersonen and update
		/*persoonService.updatePersonen(contactVoornaamLijst, contactFamilienaamLijst, contactTelefoonLijst,
				contactEmailadresLijst);*/
		
		
		// get Transportdienst and update
		Dienst dienst = dienstRepo.get(dienstId);
		dienst.setNaam(naam);
		dienst.setActief(isActief);
		
		// we moeten de ttf niet opnieuw linken, in geval van update blijft de link behouden, enkel de waarden van de ttf kunnen wijzigen
		/*try {
			TrackTraceFormat ttf = ttfService.findTtfByAll(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
					verificatiecode);
			dienst.setTrackTraceFormat(ttf);
		}
		catch(EntityNotFoundException e) {
			throw e;
		}*/
		
		// we moeten de contactpersonen well opnieuw linken, er kunnen contacten bijgekomen zijn of verdwenen zijn
		//dienst.
		// verwijder contacpersonen
		Set<Persoon> verwijderSet = dienst.getPersonen().stream()
														.filter(p -> (!contactEmailadresLijst.contains(p.getEmailAdress())))
														.collect(Collectors.toSet());
		
		verwijderSet.forEach(p -> dienst.removePerson(p));
		// moeten we ze ook verwijderen in de repo, of zorgt JPA daarvoor?
		//verwijderSet.forEach(p -> persoonService.verwijderPersoon(p.getEmailAdress()));
		
		
		
		// update or add contacpersonen		
		
		//ToDo: check all list have the same size
		//ToDo: use streams
				
		Iterator<String> voornaamIterator = contactVoornaamLijst.iterator();
		Iterator<String> familienaamIterator = contactFamilienaamLijst.iterator();
		Iterator<String> telefoonIterator = contactTelefoonLijst.iterator();
		Iterator<String> emailIterator = contactEmailadresLijst.iterator();
		
		List<String> addedPersonen = new ArrayList<>();
		while (voornaamIterator.hasNext() && familienaamIterator.hasNext() && telefoonIterator.hasNext() && emailIterator.hasNext()) {
			String email = emailIterator.next();
			Optional<Persoon> contact = persoonService.getPersoonList().stream()
	                .filter( p -> p.getEmailAdress().equalsIgnoreCase(email))
	                .findFirst();
	        if (!contact.isPresent()) {
	        	// add new contactpersonen
	        	persoonService.maakPersoon(voornaamIterator.next(), familienaamIterator.next(), telefoonIterator.next(), email);
	        	addedPersonen.add(email);
	        }
	        else {
	        	// update contactpersonen
	        	persoonService.updatePersoon(voornaamIterator.next(), familienaamIterator.next(), telefoonIterator.next(), email);
	        }
		}
		
		addedPersonen.forEach(email -> {
			Optional<Persoon> contact = persoonService.getPersoonList().stream()
	                .filter( p -> p.getEmailAdress().equalsIgnoreCase(email))
	                .findFirst();
	        if (!contact.isPresent()) {
	                throw new IllegalArgumentException("contactpersoon " + email + " komt niet voor");
	        }
			
			dienst.addPerson(contact.get());
		});
		
		dienstRepo.update(dienst);
		
		
		
		
	}
	
	public DienstDTO getDienst(long dienstId) throws IllegalArgumentException {
		try {
			Dienst dienst = this.dienstRepo.get(dienstId);
			if(dienst instanceof Transportdienst) {
				TrackTraceFormatDTO ttfDTO = new TrackTraceFormatDTO(((Transportdienst) dienst).getTrackTraceFormaat().getBarcodeLengte(),((Transportdienst) dienst).getTrackTraceFormaat().isBarcodeEnkelCijfers(),((Transportdienst) dienst).getTrackTraceFormaat().getBarcodePrefix(), ((Transportdienst) dienst).getTrackTraceFormaat().getVerificatieCode());
				Set<PersoonDTO> contacpersoonDTOSet = new HashSet<>();
				for(Persoon p: dienst.getPersonen()) {
					contacpersoonDTOSet.add(new ContactpersoonDTO(p.getVoornaam(), p.getFamilienaam(), p.getEmailAdress(), p.getTelefoonnummer()));
				}
				
				return new TransportdienstDTO(dienst.getNaam(), dienst.isActief(), contacpersoonDTOSet, ttfDTO);
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
			Set<PersoonDTO> contacpersoonDTOSet = new HashSet<>();
			for(Persoon p: d.getPersonen()) {
				contacpersoonDTOSet.add(new ContactpersoonDTO(p.getVoornaam(), p.getFamilienaam(), p.getEmailAdress(), p.getTelefoonnummer()));
			}
			TrackTraceFormatDTO ttfDTO = new TrackTraceFormatDTO(((Transportdienst)d).getTrackTraceFormaat().getBarcodeLengte(),
					((Transportdienst)d).getTrackTraceFormaat().isBarcodeEnkelCijfers(),
					((Transportdienst)d).getTrackTraceFormaat().getBarcodePrefix(),
					((Transportdienst)d).getTrackTraceFormaat().getVerificatieCode());
			dienstDTOList.add(new TransportdienstDTO(d.getNaam(), d.isActief(), contacpersoonDTOSet, ttfDTO));
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
