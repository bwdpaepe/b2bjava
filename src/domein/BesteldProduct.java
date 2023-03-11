package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BesteldProduct implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	private String naam;
	private double eenheidsprijs;
	private int aantal;
	
	@OneToOne
	private Product product;
	
	public BesteldProduct(long id, int aantal) {
		
	}

}
