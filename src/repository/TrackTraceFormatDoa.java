package repository;

import javax.persistence.EntityNotFoundException;

import domein.TrackTraceFormat;


public interface TrackTraceFormatDoa extends GenericDao<TrackTraceFormat>  {
    public TrackTraceFormat getTtfByAll(int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatieCode) throws EntityNotFoundException;

}
