package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Bedrijf;
import domein.Bestelling;


public class BestellingDaoJpa extends GenericDaoJpa<Bestelling> implements BestellingDao {

	public BestellingDaoJpa() {
		super(Bestelling.class);
	}
	
    @Override
    public List<Bestelling> getBestellingenByLeverancierID(Bedrijf leverancier) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Bestelling.getBestellingenByLeverancierID", Bestelling.class)
                 .setParameter("leverancier", leverancier)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException("Deze leverancier heeft nog geen bestellingen");
        } 
    }

	@Override
	public List<Bestelling> getBestellingInfoBijLeverancierVanKlant(Bedrijf leverancier, Bedrijf klant)
	{
		try {
            return em.createNamedQuery("Bestelling.getBestellingenByLeverancierID", Bestelling.class)
                 .setParameter("leverancier", leverancier)
                 .setParameter("klant", klant)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException("Deze klant heeft nog geen bestellingen bij deze leverancier");
        } 
	}

}
