package com.axisdesktop.bankrating.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "rating_minfin" )
public class RatingMinfin {
	@Id
	@GeneratedValue
	private int id;

}
