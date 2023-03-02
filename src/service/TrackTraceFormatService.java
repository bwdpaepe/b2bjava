package service;

import domein.TrackTraceFormat;
import repository.GenericDao;
import repository.GenericDaoJpa;
import repository.TrackTraceFormatDoaJpa;

public class TrackTraceFormatService {
	
	private TrackTraceFormatDoaJpa ttfRepo;

	public TrackTraceFormatService()
	{
		this.ttfRepo = new TrackTraceFormatDoaJpa();
	}
	
	public TrackTraceFormatService(TrackTraceFormatDoaJpa ttfRepo) {
		this.ttfRepo = ttfRepo;
	}
	
	public void maakTrackTraceFormat(int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatieCode) {
		GenericDaoJpa.startTransaction();
		ttfRepo.insert(new TrackTraceFormat(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
			verificatieCode));
		GenericDaoJpa.commitTransaction();
	}
	
	public TrackTraceFormat findTtfByAll(int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatieCode) {
		return ttfRepo.getTtfByAll(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
				verificatieCode);
	}
	
	

}
