package com.axisdesktop.bankrating.crawler.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.axisdesktop.bankrating.crawler.Parser;

public class MinfinParser implements Parser {
	private String[] raw;
	private Set<String> links;
	private Map<String, String> paging;
	private Map<String, Map<String, String>> data;

	public MinfinParser( String... raw ) {
		if( raw == null ) throw new NullPointerException( "MinfinParser( String.. raw ), raw == null" );

		this.raw = raw;
		this.links = new HashSet<>();
		this.paging = new HashMap<>();
		this.data = new HashMap<>();
	}

	@Override
	public Parser parse() {
		Document doc;

		for( String s : raw ) {
			doc = Jsoup.parse( s );

			Elements dates = doc.select( "select[name=date] option" );

			for( Element el : dates ) {
				this.paging.put( el.attr( "value" ), el.attr( "value" ) );
			}

			Elements bankRows = doc.select( "tr[title]" );

			if( bankRows.size() > 0 ) {
				for( Element bank : bankRows ) {
					Map<String, String> map = new HashMap<>();

					this.links.add( bank.select( "a[href]" ).get( 0 ).attr( "href" ) );

					Elements td = bank.select( "td" );

					Element el1 = td.get( 1 ).select( "a" ).get( 1 );
					String name = el1.attr( "href" ).replace( "/company/", "" ).replace( "/rating/", "" );
					if( name.indexOf( '?' ) != -1 ) {
						name = name.substring( 0, name.indexOf( '?' ) );
					}

					map.put( "score", td.get( 0 ).text().replaceAll( "\\W", "" ).trim() );
					map.put( "name", name.trim() );
					map.put( "title", el1.text().trim() );
					map.put( "link", el1.attr( "href" ).trim() );
					map.put( "rating", td.get( 2 ).text().replaceAll( "([^0-9.]+)", "" ).trim() );
					map.put( "stress_tolerance", td.get( 3 ).text().trim() );
					map.put( "investor_loyalty", td.get( 4 ).text().trim() );
					map.put( "analyst_correction", td.get( 5 ).text().trim() );
					map.put( "nbu_asset_size_score", td.get( 6 ).text().replaceAll( "\\W", "" ).trim() );

					this.data.put( name, map );
				}
			}
			else {
				Map<String, String> map = new HashMap<>();

				Elements trs = doc.select( "div.rating-details.base-data table tr" );

				for( Element tr : trs ) {
					map.put( tr.select( "td" ).get( 0 ).text(),
							tr.select( "td" ).get( 1 ).text().replaceAll( " млн грн", "" ) );
				}

				this.data.put( "base", map );
			}
		}

		return this;
	}

	@Override
	public HashSet<String> links() {
		return new HashSet<String>( this.links );
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public Map<String, String> paging() {
		HashMap<String, String> map = new HashMap<>( this.paging.size() );
		map.putAll( this.paging );
		return map;
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public Map<String, Map<String, String>> data() {
		HashMap<String, Map<String, String>> map = new HashMap<>( this.data.size() );
		map.putAll( this.data );
		return map;
	}

}
