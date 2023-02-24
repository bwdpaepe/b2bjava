package repository;
import domein.Medewerker;
import javax.persistence.EntityNotFoundException;

public interface MedewerkerDao extends GenericDao<Medewerker>  {
        public Medewerker getMedewerkerByEmailAdress(String emailAdress) throws EntityNotFoundException;   
}