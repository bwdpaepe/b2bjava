package domein;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import service.ValidationService;

@Entity
@Table(name = "Diensten")
@NamedQueries({
    @NamedQuery(name = "Dienst.findDienstenWithBedrijf",
                         query = "select d from Dienst d where d.bedrijf = :bedrijf")            
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")
public abstract class Dienst implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.ALL)
	protected Set<Persoon> personen = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "naam", unique = true)
	private String naam;
	@Column(name = "is_actief")
	private boolean isActief;
	@ManyToOne
	private Bedrijf bedrijf;

	// lege Constructor voor JPA
	protected Dienst()
	{

	}

	protected Dienst(String naam, Bedrijf bedrijf, boolean isActief, Persoon persoon)
	{
		setNaam(naam);
		setActief(isActief);
		setBedrijf(bedrijf);
		setPersonen(persoon);
	}

	
	
	public Bedrijf getBedrijf() {
		return bedrijf;
	}

	public final void setBedrijf(Bedrijf bedrijf)
	{
		ValidationService.controleerNietBlanco(bedrijf);
		this.bedrijf = bedrijf;
		
	}

	public long getId() {
		return id;
	}

	public String getNaam()
	{
		return naam;
	}

	public final void setNaam(String naam)
	{
		ValidationService.controleerNietBlanco(naam);
		this.naam = naam;
	}

	public boolean isActief()
	{
		return isActief;
	}

	public void setActief(boolean isActief)
	{
		this.isActief = isActief;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(naam);
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
		Dienst other = (Dienst) obj;
		return Objects.equals(naam, other.naam);
	}

	public Set<Persoon> getPersonen() {
		return Collections.unmodifiableSet(personen);
	}
	
	public void addPerson(Persoon persoon) {
		personen.add(persoon);
	}
	
	public void removePerson(Persoon persoon) {
		personen.remove(persoon);
	}
	
	private void setPersonen(Persoon persoon) {
		this.personen.add(persoon);
		
	};
}
