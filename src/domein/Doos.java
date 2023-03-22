package domein;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Enumerated(EnumType.STRING)
	private DoosType doosType;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="dimensie")
	private Dimensie dimensie;
	@Column
	private boolean isActief;
	@Column
	private double prijs;
	
	public Doos(String naam, double hoogte, double breedte, double lengte, String doosTypeString, double prijs, Bedrijf bedrijf) {
		setNaam(naam);
		setDimensie(lengte, breedte, hoogte);
		setDoosType(doosTypeString);
		setPrijs(prijs);
		setActief(true);
		setBedrijf(bedrijf);
	}
	
	public Doos() {
		
	}
	
	public void wijzigDoos(String naam, String doosTypeString, double prijs, boolean isActief) {
		setNaam(naam);

		setDoosType(doosTypeString);
		setPrijs(prijs);
		setActief(isActief);
	}
	
	public Bedrijf getBedrijf() {
		return this.bedrijf;
	}
	
	public final void setBedrijf(Bedrijf bedrijf)
	{
		ValidationService.controleerNietBlanco(bedrijf);
		this.bedrijf = bedrijf;	
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

	public void setDimensie(double lengte, double breedte, double hoogte){
		this.dimensie = new Dimensie(lengte, breedte, hoogte);
	}
	
	public Dimensie getDimensie() {
		return this.dimensie;
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
