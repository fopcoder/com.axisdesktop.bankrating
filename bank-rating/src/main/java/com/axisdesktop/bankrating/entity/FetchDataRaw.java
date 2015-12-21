package com.axisdesktop.bankrating.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table( name = "fetch_data_raw" )
@Entity
public class FetchDataRaw {
	@Id
	private long id;
	private String raw;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	public FetchDataRaw() {
	}

	@PrePersist
	private void prePersist() {
		this.created = this.modified = Calendar.getInstance();
	}

	@PreUpdate
	private void preUpdate() {
		this.modified = Calendar.getInstance();
	}

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw( String raw ) {
		this.raw = raw;
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
