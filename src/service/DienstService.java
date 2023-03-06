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
	
	//Zelfde methode als hierboven enkel een lijst van DTO's in plaats van domeinobjecten
	public TransportdienstDTO getTransportdienstDTOByID(long dienstId) throws IllegalArgumentException {
		try {

			Dienst d = this.dienstRepo.get(dienstId);
			if (d instanceof Transportdienst) {

				TransportdienstDTO tdDTO = new TransportdienstDTO((Transportdienst)d);
				return tdDTO;

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
	
	//Zelfde methode als hierboven enkel een lijst van DTO's in plaats van domeinobjecten
	public List<TransportdienstDTO> getTransportdienstenDTO() {
		List<Dienst> dienstList = dienstRepo.findAll();
		List<TransportdienstDTO> tdDTOList = new ArrayList<TransportdienstDTO>();

		for (Dienst dienst : dienstList) {
			if (dienst instanceof Transportdienst) {
				TransportdienstDTO tdDTO = new TransportdienstDTO((Transportdienst)dienst);
				tdDTOList.add(tdDTO);
			}
		}
		return tdDTOList;
	}

}
