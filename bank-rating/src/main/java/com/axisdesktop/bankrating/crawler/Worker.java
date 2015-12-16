package com.axisdesktop.bankrating.crawler;

public class Worker {
	private Crawler crawler;
	private String url;

	public Worker( Crawler crawler, String url ) {
		this.crawler = crawler;
		this.url = url;
	}

	public void start() {
		Proxy proxy = new Proxy();

		Parser minfinParser = new MinfinParser( new String[] { "gg", "gg" } );
	}
}
