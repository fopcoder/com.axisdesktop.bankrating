package com.axisdesktop.bankrating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axisdesktop.bankrating.entity.FetchData;
import com.axisdesktop.bankrating.repository.FetchDataRepository;

@Service
public class FetchDataServiceImpl implements FetchDataService {

	@Autowired
	private FetchDataRepository dataRepo;

	@Override
	public FetchData getByUrl( String url ) {
		return dataRepo.findFirstByUrl( url );
	}

	@Override
	public FetchData save( FetchData data ) {
		FetchData fd = dataRepo.findFirstByUrl( data.getUrl() );

		if( fd == null ) {
			fd = dataRepo.save( data );
		}

		return fd;
	}

	@Override
	public long count() {
		return dataRepo.count();
	}

}
