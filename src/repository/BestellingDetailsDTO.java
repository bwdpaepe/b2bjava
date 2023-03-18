package repository;

import java.util.Date;

import domein.Bestelling;

public class BestellingDetailsDTO
{
	private final long bestellingId;
	private final String orderId;
	private final Date datumGeplaatst;
	private final String status;
	
	public BestellingDetailsDTO(Bestelling bestelling)
	{
		this.bestellingId = bestelling.getId();
		this.orderId = bestelling.getOrderID();
		this.datumGeplaatst = bestelling.getDatumGeplaatst();
		this.status = bestelling.getStatus();
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

	public String getOrderId()
	{
		return orderId;
	}

	@Override
	public String toString()
	{
		return "BestellingDetailsDTO [bestellingId=" + bestellingId + ", orderId=" + orderId + ", datumGeplaatst="
				+ datumGeplaatst + ", status=" + status + "]";
	}
		
}
