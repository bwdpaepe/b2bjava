package service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import domein.Contactpersoon;
import domein.Persoon;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class PersoonService {
	
	private GenericDao<Persoon> persoonRepo;

	public PersoonService()
	{
		this.persoonRepo = new GenericDaoJpa<>(Persoon.class);
	}
	
	public PersoonService(GenericDao<Persoon> persoonRepo) {
		this.persoonRepo = persoonRepo;
	}

	public void maakPersoon(String contactVoornaam, String contactFamilienaam, String contactTelefoon,
			String contactEmailadres)
	{
		GenericDaoJpa.startTransaction();
		try {
		
			// maak de persoon
			Persoon persoon = new Contactpersoon(contactVoornaam, contactFamilienaam, contactTelefoon,
					contactEmailadres);
			persoonRepo.insert(persoon);
							
			
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
		}
		
		GenericDaoJpa.commitTransaction();
			
	}
	
	public void updatePersoon(String contactVoornaam, String contactFamilienaam, String contactTelefoon,
			String contactEmailadres)
	{
		Optional<Persoon> contact = getPersoonList().stream()
                .filter( p -> p.getEmailAdress().equalsIgnoreCase(contactEmailadres))
                .findFirst();
        if (!contact.isPresent()) {
                throw new IllegalArgumentException("contactpersoon " + contactEmailadres + " komt niet voor");
        }
		
		Persoon contactpersoon = contact.get();
		if(contactpersoon instanceof Contactpersoon) {
			contactpersoon.setVoornaam(contactVoornaam);
			contactpersoon.setFamilienaam(contactFamilienaam);
			contactpersoon.setTelefoonnummer(contactTelefoon);
			contactpersoon.setEmailAdress(contactEmailadres);
			GenericDaoJpa.startTransaction();
			persoonRepo.update(contactpersoon);
			GenericDaoJpa.commitTransaction();
		}
		
		
	}
	
	public void verwijderPersoon(String contacEmailadres) {
		Optional<Persoon> contact = getPersoonList().stream()
                .filter( p -> p.getEmailAdress().equalsIgnoreCase(contacEmailadres))
                .findFirst();
        if (!contact.isPresent()) {
                throw new IllegalArgumentException("contactpersoon " + contacEmailadres + " komt niet voor");
        }
		
		Persoon contactpersoon = contact.get();
		if(contactpersoon instanceof Contactpersoon) {
			GenericDaoJpa.startTransaction();
			persoonRepo.delete(contactpersoon);
			GenericDaoJpa.commitTransaction();
		}
	}
	
	public void updatePersonen(List<String> contactVoornaamLijst, List<String> contactFamilienaamLijst, List<String> contactTelefoonLijst,
			List<String> contactEmailadresLijst) {
		
		//ToDo: check all list have the same size
		//ToDo: use streams
		
		Iterator<String> voornaamIterator = contactVoornaamLijst.iterator();
		Iterator<String> familienaamIterator = contactFamilienaamLijst.iterator();
		Iterator<String> telefoonIterator = contactTelefoonLijst.iterator();
		Iterator<String> emailIterator = contactEmailadresLijst.iterator();
		
		while (voornaamIterator.hasNext() && familienaamIterator.hasNext() && telefoonIterator.hasNext() && emailIterator.hasNext()) {
			updatePersoon(voornaamIterator.next(), familienaamIterator.next(), telefoonIterator.next(), emailIterator.next());
		}		
	}
	
	public List<Persoon> getPersoonList(){
		List<Persoon> persoonList=persoonRepo.findAll();
        return Collections.unmodifiableList(persoonList);
    }


}
