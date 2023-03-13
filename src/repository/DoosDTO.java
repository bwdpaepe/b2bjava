package repository;

import domein.Doos;

public class DoosDTO {
	
	protected final long id;
	protected final String naam;
	protected final String doosType;
	protected final double hoogte;
	protected final double breedte;
	protected final double lengte;
	protected final boolean isActief;
	protected final double prijs;
	
	public DoosDTO(Doos doos) {
		this.id = doos.getId();
		this.naam = doos.getNaam();
		this.doosType = doos.getDoosType().toString();
		this.hoogte = doos.getHoogte();
		this.breedte = doos.getBreedte();
		this.lengte = doos.getLengte();
		this.isActief = doos.isActief();
		this.prijs = doos.getPrijs();
	}
	
	public long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public String getDoosType() {
		return doosType;
	}
	
	public double getHoogte() {
		return hoogte;
	}
	
	public double getLengte() {
		return lengte;
	}
	
	public double getBreedte() {
		return breedte;
	}
	
	public boolean isActief() {
		return isActief;
	}
	
	public double getPrijs() {
		return prijs;
	}
}
