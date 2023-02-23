package main;

import javax.persistence.EntityManager;

import domein.Medewerker;
import util.JPAUtil;

public class DBTEST {

    public static void main(String args[]) {
    	
    	Medewerker m = new Medewerker("Test", "Test", "test@gmail.com", "SomerandomHash");
    	System.out.print(m);
        

        
        //vraag aan de factory een entityManager
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        ////start een transactie
        entityManager.getTransaction().begin();
        
        ////persisteer de objecten
        entityManager.persist(m);
 
        //commit
        entityManager.getTransaction().commit();
       
        //sluit de entityManager        
        entityManager.close();
       
        //sluit de factory
        JPAUtil.getEntityManagerFactory().close();
    }

}