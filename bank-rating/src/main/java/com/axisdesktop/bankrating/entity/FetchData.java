package com.axisdesktop.bankrating.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "fetch_data" )
public class FetchData {
	@Id
	@GeneratedValue
	private int id;

	@Column( name = "fetch_status_id" )
	private int fetchStatusId;

	private String url;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
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

	public FetchData() {
	}

	public FetchData( String url, int fetch_status_id ) {
		this.fetchStatusId = fetch_status_id;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public int getFetch_status_id() {
		return fetchStatusId;
	}

	public void setFetch_status_id( int fetch_status_id ) {
		this.fetchStatusId = fetch_status_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
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

	@Override
	public String toString() {
		return "FetchData [id=" + id + ", fetch_status_id=" + fetchStatusId + ", url=" + url + ", created=" + created
				+ ", modified=" + modified + "]";
	}

}
