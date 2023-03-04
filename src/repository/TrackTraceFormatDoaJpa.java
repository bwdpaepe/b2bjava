package repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.TrackTraceFormat;


public class TrackTraceFormatDoaJpa extends GenericDaoJpa<TrackTraceFormat> implements TrackTraceFormatDoa {

	public TrackTraceFormatDoaJpa() {
		super(TrackTraceFormat.class);
	}
	
	@Override
	public TrackTraceFormat getTtfByAll(int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatieCode) throws EntityNotFoundException {
		try {
            return em.createNamedQuery("TrackTraceFormat.findTtfByAll", TrackTraceFormat.class)
                 .setParameter("barcodeLengte", barcodeLengte)
                 .setParameter("isBarcodeEnkelCijfers", isBarcodeEnkelCijfers)
                 .setParameter("barcodePrefix", barcodePrefix)
                 .setParameter("verificatieCode", verificatieCode)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

}



