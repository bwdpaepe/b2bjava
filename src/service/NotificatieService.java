package service;

import domein.Notificatie;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class NotificatieService {
	GenericDao<Notificatie> notificatieRepo;

	public NotificatieService() {
		this.notificatieRepo = new GenericDaoJpa<>(Notificatie.class);
	}

	public NotificatieService(GenericDao<Notificatie> notificatieRepo) {
		this.notificatieRepo = notificatieRepo;
	}

	public Notificatie getNotificatieById(long notificatieId) {
		Notificatie notificatie = notificatieRepo.get(notificatieId);
		if (notificatie == null) {
			throw new IllegalArgumentException("ongeldige notificatieId");
		}
		return notificatie;
	}

}
