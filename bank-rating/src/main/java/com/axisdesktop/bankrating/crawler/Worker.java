package com.axisdesktop.bankrating.crawler;

import com.axisdesktop.bankrating.crawler.impl.MinfinParser;

public class Worker {
	private Crawler crawler;
	private String url;

	public Worker( Crawler crawler, String url ) {
		this.crawler = crawler;
		this.url = url;
	}

	public void start() {
		// Proxy proxy = new Proxy();
		System.out.println( "worker" );
		// Parser minfinParser = new MinfinParser( new String[] { "gg", "gg" } );
	}
}
