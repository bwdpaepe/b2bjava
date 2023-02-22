package main;

import domein.Medewerker;
import javax.persistence.EntityManager;
import util.JPAUtil;

public class DBTEST {

    public static void main(String args[]) {
    	
    	Medewerker m = new Medewerker("Jos", "Yzendijk", "jos@gmail.com", "SomerandomHash");
    	System.out.print(m);
        

        
        //vraag aan de factory een entityManager
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        ////start een transactie
        entityManager.getTransaction().begin();
        
        ////persisteer de 3 objecten
        entityManager.persist(m);
 
        //commit
        entityManager.getTransaction().commit();
       
        //sluit de entityManager        
        entityManager.close();
       
        //sluit de factory
        JPAUtil.getEntityManagerFactory().close();
    }

}