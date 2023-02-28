package repository;


import javax.persistence.EntityNotFoundException;


import domein.User;

public interface UserDao extends GenericDao<User>  {
        public User getMedewerkerByEmailAdress(String emailAdress) throws EntityNotFoundException;


}