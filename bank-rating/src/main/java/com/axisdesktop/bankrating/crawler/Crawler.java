package com.axisdesktop.bankrating.crawler;

public class Crawler {
	private String url;

	public Crawler( String url ) {
		this.url = url;
	}

	public void start() {
		new Worker( this, url );
	}
}
