package com.axisdesktop.bankrating.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "rating" )
public class Rating {
	@Id
	@GeneratedValue
	private int id;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "bank_id", referencedColumnName = "id" )
	private Bank bank;

	@Column( updatable = false )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar created;

	@Temporal( TemporalType.TIMESTAMP )
	private Calendar modified;

	@Temporal( TemporalType.DATE )
	@Column( nullable = false )
	private Calendar date;

	@Column( nullable = false )
	private BigDecimal rating;

	@Column( nullable = false )
	private int score;

	@Column( name = "stress_tolerance", nullable = false )
	private BigDecimal stressTolerance;

	@Column( name = "investor_loyalty", nullable = false )
	private BigDecimal investorLoyalty;

	@Column( name = "analyst_correction", nullable = false )
	private BigDecimal analystCorrection;

	@Column( name = "nbu_asset_size_score", nullable = false )
	private int nbuAssetSizeScore;

	public Rating() {
	}

	public Rating( Bank bank, Map<String, String> info, String date ) {
		this.bank = bank;

		try {
			this.date = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
			this.date.setTime( sdf.parse( date ) );
		}
		catch( ParseException e ) {
			System.err.println( "date format error " + date );
			throw new IllegalArgumentException( "date format error " + date );
		}

		for( Map.Entry<String, String> entry : info.entrySet() ) {
			switch( entry.getKey() ) {
				case "score":
					this.score = Integer.parseInt( entry.getValue() );
					break;
				case "rating":
					this.rating = new BigDecimal( entry.getValue() );
					break;
				case "stress_tolerance":
					this.stressTolerance = new BigDecimal( entry.getValue() );
					break;
				case "investor_loyalty":
					this.investorLoyalty = new BigDecimal( entry.getValue() );
					break;
				case "analyst_correction":
					this.analystCorrection = new BigDecimal( entry.getValue() );
					break;
				case "nbu_asset_size_score":
					this.nbuAssetSizeScore = Integer.parseInt( entry.getValue() );
					break;
			}
		}
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

	public Bank getBankId() {
		return bank;
	}

	public void setBankId( Bank bankId ) {
		this.bank = bankId;
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

	public Calendar getDate() {
		return date;
	}

	public void setDate( Calendar date ) {
		this.date = date;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating( BigDecimal rating ) {
		this.rating = rating;
	}

	public int getScore() {
		return score;
	}

	public void setScore( int score ) {
		this.score = score;
	}

	public BigDecimal getStressTolerance() {
		return stressTolerance;
	}

	public void setStressTolerance( BigDecimal stressTolerance ) {
		this.stressTolerance = stressTolerance;
	}

	public BigDecimal getInvestorLoyalty() {
		return investorLoyalty;
	}

	public void setInvestorLoyalty( BigDecimal investorLoyalty ) {
		this.investorLoyalty = investorLoyalty;
	}

	public BigDecimal getAnalystCorrection() {
		return analystCorrection;
	}

	public void setAnalystCorrection( BigDecimal analystCorrection ) {
		this.analystCorrection = analystCorrection;
	}

	public int getNbuAssetSizeScore() {
		return nbuAssetSizeScore;
	}

	public void setNbuAssetSizeScore( int nbuAssetSizeScore ) {
		this.nbuAssetSizeScore = nbuAssetSizeScore;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank( Bank bank ) {
		this.bank = bank;
	}

}
