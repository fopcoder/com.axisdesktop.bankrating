package com.axisdesktop.bankrating.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "bank" )
public class Bank {
	@Id
	@GeneratedValue
	private int id;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	@NotNull
	@Column( nullable = false )
	private String name;

	@NotNull
	@Column( nullable = false )
	private String title;

	@NotNull
	@Column( nullable = false )
	private String url;

	// @OneToMany( mappedBy = "bank", fetch = FetchType.LAZY )
	// private List<RatingMinfin> ratings = new ArrayList<>();

	public Bank() {
	}

	public Bank( Map<String, String> map ) {
		this.name = map.get( "name" );
		this.title = map.get( "title" );
		this.url = map.get( "link" );
	}

	@PrePersist
	private void prePersist() {
		this.created = this.modified = Calendar.getInstance();
	}

	@PreUpdate
	private void preUpdate() {
		this.modified = Calendar.getInstance();
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated( Calendar created ) {
		this.created = created;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified( Calendar modified ) {
		this.modified = modified;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
	}

	public String getLink() {
		return url;
	}

	public void setLink( String link ) {
		this.url = link;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
	}

	// public List<RatingMinfin> getRatings() {
	// return ratings;
	// }
	//
	// public void setRatings( List<RatingMinfin> ratings ) {
	// this.ratings = ratings;
	// }

}
