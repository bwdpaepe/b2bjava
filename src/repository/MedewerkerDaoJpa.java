package repository;


import domein.Medewerker;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class MedewerkerDaoJpa extends GenericDaoJpa<Medewerker> implements MedewerkerDao  {
    public MedewerkerDaoJpa() {
        super(Medewerker.class);
    }

    @Override
    public Medewerker getMedewerkerByEmailAdress(String emailadress) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Medewerker.findByEmailAdress", Medewerker.class)
                 .setParameter("emailAdress", emailadress)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}
