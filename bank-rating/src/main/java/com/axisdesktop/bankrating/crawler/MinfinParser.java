package com.axisdesktop.bankrating.crawler;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MinfinParser implements Parser {
	private String[] raw;

	public MinfinParser( String... raw ) {
		if( raw == null ) throw new NullPointerException( "MinfinParser( String.. raw ), raw == null" );
		this.raw = raw;
	}

	@Override
	public Map<String, String> parse() {
		Document doc;

		for( String s : raw ) {
			doc = Jsoup.parseBodyFragment( s );

			if( true ) {

			}
			else {

			}
		}

		return null;
	}

	@Override
	public <T> T convert() {
		// TODO Auto-generated method stub
		return null;
	}

}
