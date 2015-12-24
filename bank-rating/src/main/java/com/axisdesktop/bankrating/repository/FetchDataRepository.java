package com.axisdesktop.bankrating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axisdesktop.bankrating.entity.FetchData;

public interface FetchDataRepository extends JpaRepository<FetchData, Long> {
	FetchData findFirstByUrl( String url );
}
