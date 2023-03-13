package domein;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import service.ValidationService;

@Entity
@Table(name = "Dozen", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bedrijf_id", "naam"})
})
public class Doos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Bedrijf bedrijf;
	
	@Column
	private String naam;
	@Column
	private DoosType doosType;
	@Column
	private double hoogte;
	@Column
	private double breedte;
	@Column
	private double lengte;
	@Column
	private boolean isActief;
	@Column
	private double prijs;
	
	public Doos(String naam, double hoogte, double breedte, double lengte, String doosTypeString, double prijs, Bedrijf bedrijf) {
		setNaam(naam);
		setBreedte(breedte);
		setHoogte(hoogte);
		setLengte(lengte);
		setDoosType(doosTypeString);
		setPrijs(prijs);
		setActief(true);
		setBedrijf(bedrijf);
	}
	
	public Bedrijf getBedrijf() {
		return this.bedrijf;
	}
	
	public final void setBedrijf(Bedrijf bedrijf)
	{
		if(bedrijf == null) {
			throw new IllegalArgumentException("Bedrijf voor doos is ongeldig");
		}
		
		this.bedrijf = bedrijf;
		
	}

	public Doos() {
		
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

	public void setDoosType(String doosTypeString) {
		this.doosType = switch (doosTypeString.toLowerCase()) {
		case "standaard" -> DoosType.STANDAARD;
		case "custom" -> DoosType.CUSTOM;
		default -> throw new IllegalArgumentException("Unexpected value: " + doosTypeString.toLowerCase());
		};
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
