package service;

import javax.persistence.EntityNotFoundException;

import domein.TrackTraceFormat;
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
	
	public void updateTrackTraceFormat(int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatieCode) throws IllegalArgumentException {
		try {
			TrackTraceFormat ttf = findTtfByAll(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
				verificatieCode);
			ttf.setBarcodeLengte(barcodeLengte);
			ttf.setIsBarcodeEnkelCijfers(isBarcodeEnkelCijfers);
			ttf.setBarcodePrefix(barcodePrefix);
			ttf.setVerificatieCode(verificatieCode);
			GenericDaoJpa.startTransaction();
			ttfRepo.update(ttf);
			GenericDaoJpa.commitTransaction();
		}
		catch(EntityNotFoundException e) {
			throw new IllegalArgumentException("Er is geen Tracktraceformaat met de gevraagde parameters.");
		}
	}
	
	public TrackTraceFormat findTtfByAll(int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatieCode) throws EntityNotFoundException {
		try {
		return ttfRepo.getTtfByAll(barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
				verificatieCode);
		}
		catch(EntityNotFoundException e) {
			throw e;
		}
	}
	
	

}
