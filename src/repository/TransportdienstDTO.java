package repository;

import java.util.Set;

public class TransportdienstDTO extends DienstDTO {
	
	private final TrackTraceFormatDTO trackTraceFormaatDTO;
	
	public TransportdienstDTO(String naam, boolean isActief, Set<PersoonDTO> personen, TrackTraceFormatDTO ttfDTO) {
		super(naam, isActief, personen);
		this.trackTraceFormaatDTO = ttfDTO;
	}

	public TrackTraceFormatDTO getTrackTraceFormaatDTO() {
		return trackTraceFormaatDTO;
	}

	@Override
	public String toString() {
		return "trackTraceFormaat=" + trackTraceFormaatDTO.toString() + " " + super.toString();
	}
	
	

}
