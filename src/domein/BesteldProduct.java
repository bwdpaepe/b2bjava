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
@Table(name = "BesteldeProducten", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "bestelling_id"})
})
public class BesteldProduct implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String naam;
	@Column
	private double eenheidsprijs;
	@Column
	private int aantal;
	
	@ManyToOne
	private Product product;
	@ManyToOne
	private Bestelling bestelling;
	
	protected BesteldProduct() {};
	
	public BesteldProduct(Product product, int aantal, Bestelling bestelling) {
		// naam en prijs worden hier apart opgeslagen voor geval product zou veranderen na de bestelling
		setNaam(product.getNaam());
		setEenheidsprijs(product.getEenheidsprijs());
		setAantal(aantal);
		setProduct(product);
		setBestelling(bestelling);
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	private void setNaam(String naam) {
		ValidationService.controleerNietBlanco(naam);
		this.naam = naam;
	}

	public double getEenheidsprijs() {
		return eenheidsprijs;
	}

	private void setEenheidsprijs(double eenheidsprijs) {
		ValidationService.controleerGroterDanNul(eenheidsprijs);
		this.eenheidsprijs = eenheidsprijs;
	}

	public int getAantal() {
		return aantal;
	}

	private void setAantal(int aantal) {
		ValidationService.controleerGroterDanNul(aantal);
		this.aantal = aantal;
	}

	public Product getProduct() {
		return product;
	}

	private void setProduct(Product product) {
		ValidationService.controleerNietBlanco(product);
		this.product = product;
	}

	public Bestelling getBestelling()
	{
		return bestelling;
	}

	private void setBestelling(Bestelling bestelling)
	{
		ValidationService.controleerNietBlanco(bestelling);
		this.bestelling = bestelling;
	}
}
