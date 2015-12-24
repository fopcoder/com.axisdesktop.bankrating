package com.axisdesktop.bankrating.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.axisdesktop.bankrating.entity.FetchData;
import com.axisdesktop.bankrating.repository.FetchDataRepository;

public class FetchDataServiceImpl implements FetchDataService {

	@Autowired
	private FetchDataRepository dataRepo;

	@Override
	public FetchData getByUrl( String url ) {
		return dataRepo.findFirstByUrl( url );
	}

	@Override
	public FetchData save( FetchData data ) {
		return dataRepo.save( data );
	}
}
