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
	
	protected BesteldProduct() {};
	
	public BesteldProduct(long id, int aantal) {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public double getEenheidsprijs() {
		return eenheidsprijs;
	}

	public void setEenheidsprijs(double eenheidsprijs) {
		this.eenheidsprijs = eenheidsprijs;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
