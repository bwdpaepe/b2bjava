package domein;

import java.util.Date;

public class BestellingDetails
{
	private final long bestellingId;
	private final Date datumGeplaatst;
	private final String status;
	
	public BestellingDetails(long bestellingId, Date datumGeplaatst, BestellingStatus status)
	{
		this.bestellingId = bestellingId;
		this.datumGeplaatst = datumGeplaatst;
		this.status = status.toString();
	}

	public long getBestellingId()
	{
		return bestellingId;
	}

	public Date getDatumGeplaatst()
	{
		return datumGeplaatst;
	}

	public String getStatus()
	{
		return status;
	}

}
