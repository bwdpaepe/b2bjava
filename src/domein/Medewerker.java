package domein;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import service.ValidationService;
import util.AankoperFunctie;
import util.AdminFunctie;
import util.Functie;
import util.MagazijnierFunctie;

@Entity
@DiscriminatorValue("Medewerker")
public class Medewerker extends User
{

	private static final long serialVersionUID = 1L;

	@Column(name = "personeelsNr")
	private final int personeelsNr;

	@Column(name = "Functie")
	private String functieString;

	@Transient
	private Functie functie;

	public Medewerker(String voornaam, String familienaam, String email, String password, String adres,
			String telefoonnummer, int personeelsNr, String functie, Bedrijf bedrijf)
	{
		super(voornaam, familienaam, email, password, telefoonnummer, adres, bedrijf);
		setFunctie(functie);
		ValidationService.controleerGroterDanNul(personeelsNr); 
		this.personeelsNr = personeelsNr;  // mag niet meer veranderen na creatie
	}

	// Lege constructor nodig voor JPA
	public Medewerker()
	{
		super();
		this.personeelsNr = -1;
	}

	public String getFunctie()
	{
		return this.functieString;
	}

	public final void setFunctie(String functie)
	{
		ValidationService.controleerNietBlanco(functie);

		this.functie = switch (functie.toLowerCase())
			{
			case "magazijnier" -> new MagazijnierFunctie();
			case "admin" -> new AdminFunctie();
			case "aankoper" -> new AankoperFunctie();
			default -> throw new IllegalArgumentException("Unexpected value: " + functie.toLowerCase());

			};

		this.functieString = this.functie.toString();
	}

	public int getPersoneelsNr()
	{
		return personeelsNr;
	}
}
