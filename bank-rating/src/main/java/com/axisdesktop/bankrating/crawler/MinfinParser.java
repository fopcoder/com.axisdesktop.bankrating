package com.axisdesktop.bankrating.crawler;

import java.util.Map;

public class MinfinParser implements Parser {
	private String[] raw;

	public MinfinParser( String[] raw ) {
		this.raw = raw;
	}

	@Override
	public Map<String, String> parse() {

		for( String s : raw ) {

		}

		return null;
	}

	@Override
	public <T> T convert() {
		// TODO Auto-generated method stub
		return null;
	}

}
