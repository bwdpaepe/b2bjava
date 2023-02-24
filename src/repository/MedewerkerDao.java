package repository;
import javax.persistence.EntityNotFoundException;

import domein.Medewerker;

public interface MedewerkerDao extends GenericDao<Medewerker>  {
        public Medewerker getMedewerkerByEmailAdress(String emailAdress) throws EntityNotFoundException;


}