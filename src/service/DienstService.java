package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import domein.Bedrijf;
import domein.Contactpersoon;
import domein.Dienst;
import domein.Persoon;
import domein.TrackTraceFormat;
import domein.Transportdienst;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DienstService {
	private GenericDao<Dienst> dienstRepo;

	public DienstService() {
		this.dienstRepo = new GenericDaoJpa<>(Dienst.class);
	}

	public DienstService(GenericDao<Dienst> dienstRepo) {
		this.dienstRepo = dienstRepo;
	}

	public void maakTransportdienst(String naam, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatiecode, String contactVoornaam, String contactFamilienaam,
			String contactTelefoon, String contactEmailadres, Bedrijf bedrijf) {
		GenericDaoJpa.startTransaction();
		try {
			
			//maak Contactpersoon
			Contactpersoon contactpersoon = new Contactpersoon(contactVoornaam, contactFamilienaam, contactEmailadres, contactTelefoon);
			// new ttf
			TrackTraceFormat ttf = new TrackTraceFormat(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
					verificatiecode);

			// maak de transportdienst
			Transportdienst transportdienst = new Transportdienst(naam, bedrijf, contactpersoon, ttf);

			transportdienst.setTrackTraceFormat(ttf);


			dienstRepo.insert(transportdienst);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			System.err.println(e);
			GenericDaoJpa.rollbackTransaction();
		}

	}

	public void wijzigActivatieDienst(long dienstId, boolean isActief) throws IllegalArgumentException {
		try {
			Dienst d = this.dienstRepo.get(dienstId);
			if (d instanceof Transportdienst) {

				d.setActief(isActief);
				GenericDaoJpa.startTransaction();
				this.dienstRepo.update(d);
				GenericDaoJpa.commitTransaction();

			} else {
				throw new IllegalArgumentException("Ongeldig diensttype");
			}
		} catch (EntityNotFoundException ex) {
			throw new IllegalArgumentException("De gevraagde dienst bestaat niet");
		}

	}

	public Transportdienst getTransportdienstByID(long dienstId) throws IllegalArgumentException {
		try {

			Dienst d = this.dienstRepo.get(dienstId);
			if (d instanceof Transportdienst) {

				return (Transportdienst) d;

			} else {
				throw new IllegalArgumentException("Ongeldig diensttype");
			}
		} catch (EntityNotFoundException ex) {
			throw new IllegalArgumentException("De gevraagde dienst bestaat niet");
		}

	}

	public List<Transportdienst> getTransportdiensten() {
		List<Dienst> dienstList = dienstRepo.findAll();
		List<Transportdienst> tdList = new ArrayList<Transportdienst>();

		for (Dienst dienst : dienstList) {
			if (dienst instanceof Transportdienst) {
				tdList.add((Transportdienst) dienst);
			}
		}
		return tdList;
	}
	
	public void addContactpersoon(String contactVoornaam, String contactFamilienaam,
			String contactTelefoon, String contactEmailadres, long dienstId) {
		try {
			Dienst d = this.dienstRepo.get(dienstId);
			if (d instanceof Transportdienst) {
				//maak Contactpersoon
				Contactpersoon contactpersoon = new Contactpersoon(contactVoornaam, contactFamilienaam, contactEmailadres, contactTelefoon);

				d.addPerson(contactpersoon);
				GenericDaoJpa.startTransaction();
				this.dienstRepo.update(d);
				GenericDaoJpa.commitTransaction();

			} else {
				throw new IllegalArgumentException("Ongeldig diensttype");
			}
		} catch (EntityNotFoundException ex) {
			throw new IllegalArgumentException("De gevraagde dienst bestaat niet");
		}
		
	}
	
	public void removeContactpersoon(long persoonId, long dienstId) {
		try {
			Dienst d = this.dienstRepo.get(dienstId);
			if (d instanceof Transportdienst) {
				Set<Persoon> cpSet = d.getPersonen();
				//get Contactpersoon
				Persoon cp = cpSet.stream()
										 .filter(p -> persoonId == p.getId())
										 .findFirst()
										 .get();
				if(Objects.isNull(cp)) {
					throw new IllegalArgumentException("De gevraagde contactpersoon bestaat niet");
				}
				
				if(cp instanceof Contactpersoon) {
					//remove Contactpersoon
					d.removePerson(cp);
					GenericDaoJpa.startTransaction();
					this.dienstRepo.update(d);
					GenericDaoJpa.commitTransaction();
					
				}
				else {
					throw new IllegalArgumentException("Ongeldig persoontype");
				}
				
			} else {
				throw new IllegalArgumentException("Ongeldig diensttype");
			}
		} catch (EntityNotFoundException ex) {
			throw new IllegalArgumentException("De gevraagde dienst bestaat niet");
		}
		
	}
	
	public void updateTransportdienst(String naam, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatiecode, long dienstId) {
		try {
			Dienst d = this.dienstRepo.get(dienstId);
			if (d instanceof Transportdienst) {
				d.setNaam(naam);
				TrackTraceFormat ttf = ((Transportdienst)d).getTrackTraceFormat();
				ttf.setBarcodeLengte(barcodeLengte);
				ttf.setIsBarcodeEnkelCijfers(isBarcodeEnkelCijfers);
				ttf.setBarcodePrefix(barcodePrefix);
				ttf.setVerificatieCode(verificatiecode);
				((Transportdienst)d).setTrackTraceFormat(ttf);
				GenericDaoJpa.startTransaction();
				this.dienstRepo.update(d);
				GenericDaoJpa.commitTransaction();
				
			} else {
				throw new IllegalArgumentException("Ongeldig diensttype");
			}
		} catch (EntityNotFoundException ex) {
			throw new IllegalArgumentException("De gevraagde dienst bestaat niet");
		}
	}

}
