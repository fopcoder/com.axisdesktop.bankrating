package com.axisdesktop.bankrating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axisdesktop.bankrating.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {
	Bank findFirstByName( String name );

}
