package com.axisdesktop.bankrating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axisdesktop.bankrating.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
