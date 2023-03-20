package domein;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import service.ValidationService;

@Entity
public class Notificatie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private Date creationDate;
	@Column
	private boolean isBekenen;

	// RELATIES

	@ManyToOne
	private Medewerker aankoper;

	@OneToOne
	private Bestelling bestelling;

	public Notificatie() {

	}

	public Notificatie(Date creationDate, boolean isBekenen, Medewerker aankoper, Bestelling bestelling) {
		setCreationDate(creationDate);
		setBekenen(isBekenen);
		setAankoper(aankoper);
		setBestelling(bestelling);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		ValidationService.controleerDatumNietInVerleden(creationDate);
		this.creationDate = creationDate;
	}

	public boolean isBekenen() {
		return isBekenen;
	}

	public void setBekenen(boolean isBekenen) {
		this.isBekenen = isBekenen;
	}

	public Medewerker getAankoper() {
		return aankoper;
	}

	public void setAankoper(Medewerker aankoper) {
		this.aankoper = aankoper;
	}

	public long getId() {
		return id;
	}

	public Bestelling getBestelling() {
		return bestelling;
	}

	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	}

}
