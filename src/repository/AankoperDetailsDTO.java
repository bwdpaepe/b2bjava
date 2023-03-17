package repository;

import domein.User;

public class AankoperDetailsDTO
{
	private final long aankoperId;
	private final String voornaam;
	private final String familienaam;
	private final String emailadres;
	
	public AankoperDetailsDTO(User aankoper)
	{
		this.aankoperId = aankoper.getId();
		this.voornaam = aankoper.getVoornaam();
		this.familienaam = aankoper.getFamilienaam();
		this.emailadres = aankoper.getEmail();
	}

	public String getVoornaam()
	{
		return voornaam;
	}

	public String getFamilienaam()
	{
		return familienaam;
	}

	public String getEmailadres()
	{
		return emailadres;
	}

	@Override
	public String toString()
	{
		return "AankoperDetailsDTO [aankoperId=" + aankoperId + ", voornaam=" + voornaam + ", familienaam="
				+ familienaam + ", emailadres=" + emailadres + "]";
	}

}
