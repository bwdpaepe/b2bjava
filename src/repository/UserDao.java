package repository;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.AankoperDetails;
import domein.User;

public interface UserDao extends GenericDao<User>  {
        public User getMedewerkerByEmailAdress(String emailAdress) throws EntityNotFoundException;

		public List<AankoperDetails> getAankopersFromCompany(long klantId);


}