package domein;

// GEEN RECORD GEBRUIKEN hiervoor, 
// "EclipseLink implementation of JPA does not support records in the constructor expression"

public class KlantEnAantalBestellingen {
    private final Bedrijf bedrijf;
    private final Long aantalOpenBestellingen;
    
    public KlantEnAantalBestellingen(Bedrijf bedrijf, Long aantalOpenBestellingen) {
        this.bedrijf = bedrijf;
        this.aantalOpenBestellingen = aantalOpenBestellingen;
    }
    
    public Bedrijf getBedrijf() {
        return bedrijf;
    }
    
    public Long getAantalOpenBestellingen() {
        return aantalOpenBestellingen;
    }
}