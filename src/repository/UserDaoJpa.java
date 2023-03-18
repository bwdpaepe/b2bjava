package repository;


import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Medewerker;
import domein.User;

public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao  {
    public UserDaoJpa() {
        super(User.class);
    }

    @Override
    public User getMedewerkerByEmailAdress(String emailadress) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("User.findByEmailAdress", Medewerker.class)
                 .setParameter("emailAdress", emailadress)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException("Gebruiker bestaat niet");
        } 
    };

	@Override
	public List<User> getAankopersFromCompany(long klantId)
	{
		try {
		return em.createNamedQuery("User.findAankopersByBedrijfId", User.class)
				.setParameter("klantId", klantId)
				.getResultList();
		} catch (NoResultException ex) {
			 throw new EntityNotFoundException("Geen aankopers gevonden");
		}
	}
    
}
