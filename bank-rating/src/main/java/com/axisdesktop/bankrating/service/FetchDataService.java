package com.axisdesktop.bankrating.service;

import com.axisdesktop.bankrating.entity.Bank;
import com.axisdesktop.bankrating.entity.FetchData;
import com.axisdesktop.bankrating.entity.RatingMinfin;

public interface FetchDataService {

	FetchData getDataByUrl( String url );

	FetchData saveData( FetchData data );

	long countData();

	// Bank getBankByName( String bank );
	Bank saveBank( Bank bank );

	RatingMinfin saveRating( RatingMinfin rating );
}
