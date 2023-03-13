package repository;

import domein.BesteldProduct;

public class BesteldProductDTO {
	
	protected final long id;
	protected final String naam;
	protected final double eenheidsprijs;
	protected final int aantal;
	
	public BesteldProductDTO(BesteldProduct besteldProduct) {
		this.id = besteldProduct.getId();
		this.naam = besteldProduct.getNaam();
		this.eenheidsprijs = besteldProduct.getEenheidsprijs();
		this.aantal = besteldProduct.getAantal();
	}
	
	public long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public double getEenheidsprijs() {
		return eenheidsprijs;
	}
	
	public int getAantal() {
		return aantal;
	}
}
