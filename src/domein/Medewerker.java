package domein;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import util.AdminFunctie;
import util.Functie;
import util.MagazijnierFunctie;

@Entity
@DiscriminatorValue("Medewerker")
public class Medewerker extends User
{

	private static final long serialVersionUID = 1L;

	@Column(name = "personeelsNr", unique = true)
	private int personeelsNr;
	
	@Column(name = "Functie")
	private String functieString;

	@Transient
	private Functie functie;
	
	@ManyToOne
	private List<Dienst> diensten = new ArrayList<>();

	public Medewerker(String voornaam, String familienaam, String email, String password, String adres,
			String telefoonnummer, int personeelsNr, String functie)
	{
		super(voornaam, familienaam, email, password, telefoonnummer, adres);
		setFunctie(functie);
		setPersoneelsNr(personeelsNr);
	}

	// Lege constructor nodig voor JPA
	public Medewerker()
	{
		super();
	}

	public String getFunctie()
	{
		return this.functieString;
	}

	public final void setFunctie(String functie)
	{
		if (functie == null || functie.isBlank())
		{
			throw new IllegalArgumentException("This function is not valid, please choose Admin or Magazijnier");
		}

		switch (functie.toLowerCase())
			{
			case "magazijnier":
				this.functie = new MagazijnierFunctie();
				break;
			case "admin":
				this.functie = new AdminFunctie();
				break;

			default:
				throw new IllegalArgumentException("This function is not valid, please choose Admin or Magazijnier");
			}

		this.functieString = this.functie.toString();
	}
	
	public int getPersoneelsNr()
	{
		return personeelsNr;
	}

	public final void setPersoneelsNr(int personeelsNR)
	{
		if (personeelsNR <= 0)
		{// eventueel nog andere checks toevoegen
			throw new IllegalArgumentException("Personeelnummer is ongeldig");
		}
		this.personeelsNr = personeelsNR;
	}
	
	public List<Dienst> getDiensten() {
		return Collections.unmodifiableList(diensten);
	}

}
