package com.axisdesktop.bankrating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axisdesktop.bankrating.entity.Bank;
import com.axisdesktop.bankrating.entity.FetchData;
import com.axisdesktop.bankrating.entity.RatingMinfin;
import com.axisdesktop.bankrating.repository.BankRepository;
import com.axisdesktop.bankrating.repository.FetchDataRepository;
import com.axisdesktop.bankrating.repository.RatingMinfinRepository;

@Service
public class FetchDataServiceImpl implements FetchDataService {

	@Autowired
	private FetchDataRepository dataRepo;

	@Autowired
	private BankRepository bankRepo;

	@Autowired
	private RatingMinfinRepository minfinRepo;

	@Override
	public FetchData getDataByUrl( String url ) {
		return dataRepo.findFirstByUrl( url );
	}

	@Override
	@Transactional
	public FetchData saveData( FetchData data ) {
		FetchData fd = dataRepo.findFirstByUrl( data.getUrl() );

		if( fd == null ) {
			fd = dataRepo.save( data );
		}

		return fd;
	}

	@Override
	public long countData() {
		return dataRepo.count();
	}

	@Override
	@Transactional
	public Bank saveBank( Bank bank ) {
		Bank b = bankRepo.findFirstByName( bank.getName() );

		if( b == null ) {
			b = bankRepo.save( bank );
		}

		return b;
	}

	@Override
	public RatingMinfin saveRating( RatingMinfin rating ) {
		return minfinRepo.save( rating );
	}
}
