package com.axisdesktop.bankrating.service;

import com.axisdesktop.bankrating.entity.FetchData;

public interface FetchDataService {

	FetchData getByUrl( String url );

	FetchData save( FetchData data );

	long count();
}
