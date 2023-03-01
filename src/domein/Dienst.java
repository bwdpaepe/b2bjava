package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import service.ValidationService;

@Entity
@Table(name = "Diensten")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")
public abstract class Dienst implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "naam", unique = true)
	private String naam;
	@Column(name = "is_actief")
	private boolean isActief;

	// lege Constructor voor JPA
	protected Dienst()
	{

	}

	protected Dienst(String naam, boolean isActief)
	{
		setNaam(naam);
		setActief(isActief);
	}

	public String getNaam()
	{
		return naam;
	}

	protected final void setNaam(String naam)
	{
		ValidationService.controleerNaam(naam);
		this.naam = naam;
	}

	public boolean isActief()
	{
		return isActief;
	}

	protected void setActief(boolean isActief)
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

}
