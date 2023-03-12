package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import service.ValidationService;

@Entity
@Table(name = "Producten")
public class Product  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Naam")
	private String naam;
	@Column(name = "Eenheidsprijs")
	private double eenheidsprijs;
	
	@ManyToOne
	@JoinColumn(name = "bedrijf", nullable = false)
	private Bedrijf leverancier;
	
	
	protected Product() {};
	
	public Product(String naam, double eenheidsprijs) {
		setNaam(naam);
		setEenheidsprijs(eenheidsprijs);
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		ValidationService.controleerNietBlanco(naam);
		this.naam = naam;
	}

	public double getEenheidsprijs() {
		return eenheidsprijs;
	}

	public void setEenheidsprijs(double eenheidsprijs) {
		ValidationService.controleerGroterDanNul(eenheidsprijs);
		this.eenheidsprijs = eenheidsprijs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(naam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(naam, other.naam);
	}
	
	
}
