package service;

import java.util.List;

import domein.Bedrijf;

import domein.Doos;

import repository.DoosDao;
import repository.DoosDaoJpa;

import repository.GenericDaoJpa;

public class DoosService {
	DoosDao doosRepo;
	BedrijfService bedrijfService;

	public DoosService() {
		this.doosRepo = new DoosDaoJpa();
		this.bedrijfService = new BedrijfService();
	}

	public Doos getDoosById(long doosId) {
		Doos doos = doosRepo.get(doosId);
		if (doos == null) {
			throw new IllegalArgumentException("ongeldige doosId");
		}
		return doos;
	}

	public void maakDoos(Doos doos) {
		GenericDaoJpa.startTransaction();
		try {
			doosRepo.insert(doos);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new InternalError(
					"Er ging iets mis met het aanmaken van de doos, gelieve opnieuw te proberen" + e.getMessage());
		}

	}

	public void wijzigDoos(long doosID, String naam, double lengte, double breedte, double hoogte, String doostype,
			double prijs, boolean isActief) {
		Doos doos = getDoosById(doosID);
		doos.wijzigDoos(naam, hoogte, breedte, lengte, doostype, prijs, isActief);
		GenericDaoJpa.startTransaction();
		try {
			doosRepo.update(doos);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new InternalError(
					"Er ging iets mis met het wijzigen van de doos, gelieve opnieuw te proberen" + e.getMessage());
		}

	}

	public List<Doos> getDozen(long bedrijfID) {
		Bedrijf bedrijf = bedrijfService.getBedrijfById(bedrijfID);

		List<Doos> dozen = doosRepo.getDozenVanBedrijf(bedrijf);

		return dozen;
	}
}
