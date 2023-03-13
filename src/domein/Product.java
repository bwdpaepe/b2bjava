package domein;

import java.io.Serializable;
import java.util.Objects;

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
@Table(name = "Producten", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"leverancier_id", "naam"})
})
public class Product  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String naam;
	@Column
	private double eenheidsprijs;
	
	@ManyToOne
	private Bedrijf leverancier;
	
	
	public Product() {};
	
	public Product(String naam, double eenheidsprijs, Bedrijf leverancier) {
		setNaam(naam);
		setEenheidsprijs(eenheidsprijs);
		setLeverancier(leverancier);
	}

	private void setLeverancier(Bedrijf leverancier)
	{
		ValidationService.controleerNietBlanco(leverancier);
		this.leverancier = leverancier;
	}

	public String getNaam() {
		return naam;
	}

	public final void setNaam(String naam) {
		ValidationService.controleerNietBlanco(naam);
		this.naam = naam;
	}

	public double getEenheidsprijs() {
		return eenheidsprijs;
	}

	public final void setEenheidsprijs(double eenheidsprijs) {
		ValidationService.controleerGroterDanNul(eenheidsprijs);
		this.eenheidsprijs = eenheidsprijs;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(leverancier, naam);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(leverancier, other.leverancier) && Objects.equals(naam, other.naam);
	}

	@Override
	public String toString()
	{
		return "Product [id=" + id + ", naam=" + naam + ", eenheidsprijs=" + eenheidsprijs + ", leverancier="
				+ leverancier + "]";
	}

	
	
	
}
