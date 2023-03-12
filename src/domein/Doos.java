package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import service.ValidationService;

@Entity
@Table(name = "Dozen")
public class Doos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String naam;
	private DoosType doosType;
	private double hoogte;
	private double breedte;
	private double lengte;
	private boolean isActief;
	private double prijs;
	
	public Doos(String naam, double hoogte, double breedte, double lengte, DoosType type, double prijs) {
		setNaam(naam);
		setBreedte(breedte);
		setHoogte(hoogte);
		setLengte(lengte);
		setDoosType(type);
		setPrijs(prijs);
		setActief(true);
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		ValidationService.controleerNietBlanco(naam);
		this.naam = naam;
	}

	public DoosType getDoosType() {
		return doosType;
	}

	public void setDoosType(DoosType doosType) {
		this.doosType = doosType;
	}

	public double getHoogte() {
		return hoogte;
	}

	public void setHoogte(double hoogte) {
		ValidationService.controleerGroterDanNul(hoogte);
		this.hoogte = hoogte;
	}

	public double getBreedte() {
		return breedte;
	}

	public void setBreedte(double breedte) {
		ValidationService.controleerGroterDanNul(breedte);
		this.breedte = breedte;
	}

	public double getLengte() {
		return lengte;
	}

	public void setLengte(double lengte) {
		ValidationService.controleerGroterDanNul(lengte);
		this.lengte = lengte;
	}

	public boolean isActief() {
		return isActief;
	}

	public void setActief(boolean isActief) {
		this.isActief = isActief;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		ValidationService.controleerGroterDanNul(prijs);
		this.prijs = prijs;
	}

	public long getId() {
		return id;
	}
	
	
	
}
