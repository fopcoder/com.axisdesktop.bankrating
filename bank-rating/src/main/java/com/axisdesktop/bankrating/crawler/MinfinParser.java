package com.axisdesktop.bankrating.crawler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
					map.put( "score", td.get( 0 ).text() );
					map.put( "name", td.get( 1 ).text() );
					map.put( "link", td.get( 1 ).select( "a" ).first().attr( "href" ) );
					map.put( "rating", td.get( 2 ).text() );
					map.put( "stress", td.get( 3 ).text() );
					map.put( "loyal", td.get( 4 ).text() );
					map.put( "correct", td.get( 5 ).text() );
					map.put( "naktivnbu", td.get( 6 ).text() );

					System.out.println( map );
					this.data.put( td.get( 1 ).text(), map );
					// for( Element cell : td ) {
					// System.out.println( cell.text() );
					//
					// }
				}
			}
			else {

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

	@Override
	public <T> T convert() {
		// TODO Auto-generated method stub
		return null;
	}

}
