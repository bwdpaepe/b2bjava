package domein;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import service.ValidationService;

@Entity
@Table(name ="dimensies")
public class Dimensie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	@Column
	private double lengte;
	@Column
	private double breedte;
	@Column
	private double hoogte;

	public Dimensie(double lengte, double breedte, double hoogte) {
		setLengte(lengte);
		setBreedte(breedte);
		setHoogte(hoogte);
	}
	
	public Dimensie() {
		
	};
	
	public long getId() {
		return this.id;
	}

	public double getLengte() {
		return lengte;
	}

	public void setLengte(double lengte) {
		ValidationService.controleerGroterDanNul(lengte);
		this.lengte = lengte;
	}

	public double getBreedte() {
		return breedte;
	}

	public void setBreedte(double breedte) {
		ValidationService.controleerGroterDanNul(breedte);
		this.breedte = breedte;
	}

	public double getHoogte() {
		return hoogte;
	}

	public void setHoogte(double hoogte) {
		ValidationService.controleerGroterDanNul(hoogte);
		this.hoogte = hoogte;
	}

	@Override
	public int hashCode() {
		return Objects.hash(breedte, hoogte, lengte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dimensie other = (Dimensie) obj;
		return Double.doubleToLongBits(breedte) == Double.doubleToLongBits(other.breedte)
				&& Double.doubleToLongBits(hoogte) == Double.doubleToLongBits(other.hoogte)
				&& Double.doubleToLongBits(lengte) == Double.doubleToLongBits(other.lengte);
	}

	@Override
	public String toString() {
		return "Dimensie [id=" + id + ", lengte=" + lengte + ", breedte=" + breedte + ", hoogte=" + hoogte + "]";
	}
	
	
	
	
	
	

}
