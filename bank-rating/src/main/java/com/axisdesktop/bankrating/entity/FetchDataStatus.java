package com.axisdesktop.bankrating.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table( name = "fetch_data_status" )
@Entity
public class FetchDataStatus {
	@Id
	private int id;

	private String name;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( updatable = false )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

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

	public String getRaw() {
		return name;
	}

	public void setRaw( String raw ) {
		this.name = raw;
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
}
