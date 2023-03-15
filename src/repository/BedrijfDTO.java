package repository;


import java.util.HashSet;
import java.util.Set;

import domein.Bedrijf;
import domein.Dienst;
import domein.Medewerker;
import domein.Transportdienst;
import domein.User;


public class BedrijfDTO {

	protected final Set<MedewerkerDTO> users;
	protected final Set<TransportdienstDTO> transportdiensten;
	protected final long id;
	protected final String naam;
	protected final String straat;
	protected final String huisnummer;
	protected final String postcode;
	protected final String stad;
	protected final String land;
	protected final String telefoonnummer;
	protected final String logo_filename;

	public BedrijfDTO( Bedrijf bedrijf) {
		this.users = setMedewerkerDTO(bedrijf);
		this.transportdiensten = setTransportdiensten(bedrijf);
		this.id = bedrijf.getID();
		this.naam = bedrijf.getNaam();
		this.straat = bedrijf.getStraat();
		this.huisnummer = bedrijf.getHuisnummer();
		this.postcode = bedrijf.getPostcode();
		this.stad = bedrijf.getStad();
		this.land = bedrijf.getLand();
		this.telefoonnummer = bedrijf.getTelefoonnummer();
		this.logo_filename = bedrijf.getLogo_filename();
	}

	public Set<MedewerkerDTO> getUsers() {
		return users;
	}

	public Set<TransportdienstDTO> getTransportdiensten() {
		return transportdiensten;
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getStad() {
		return stad;
	}

	public String getLand() {
		return land;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	public String getLogo_filename() {
		return logo_filename;
	}
	

	private final Set<MedewerkerDTO> setMedewerkerDTO(Bedrijf bedrijf) {
		Set<MedewerkerDTO> medewerkers = new HashSet<>();
		for (User u : bedrijf.getUsers()) {
			if(u instanceof Medewerker && ((Medewerker) u).getFunctie() == "Aankoper") {
				medewerkers.add(new MedewerkerDTO((Medewerker)u));
			}
		}
		return medewerkers;
	}
	
	private final Set<TransportdienstDTO> setTransportdiensten(Bedrijf bedrijf){
		Set<TransportdienstDTO> transportdiensten = new HashSet<>();
		for (Dienst td : bedrijf.getTransportdiensten()) {
			if(td instanceof Transportdienst) {
				transportdiensten.add(new TransportdienstDTO((Transportdienst)td));
			}

		}
		return transportdiensten;
	}

	@Override
	public String toString() {
		return "BedrijfDTO [users=" + users + ", transportdiensten=" + transportdiensten + ", id=" + id + ", naam="
				+ naam + ", straat=" + straat + ", huisnummer=" + huisnummer + ", postcode=" + postcode + ", stad="
				+ stad + ", land=" + land + ", telefoonnummer=" + telefoonnummer + ", logo_filename=" + logo_filename
				+ "]";
	}
	
	
	
	
	

}
