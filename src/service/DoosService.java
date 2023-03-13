package service;

import domein.Doos;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DoosService
{
  GenericDao<Doos> doosRepo;
  
  public DoosService() {
	  this.doosRepo = new GenericDaoJpa<>(Doos.class);
  }
  
  public DoosService(GenericDao<Doos> doosRepo) {
	  this.doosRepo = doosRepo;
  }
  
  public Doos getDoosById(long doosId) {
	  Doos doos = doosRepo.get(doosId);
	  if(doos == null) {
		  throw new IllegalArgumentException("ongeldige doosId");
	  }
	  return doos;
  }
}
