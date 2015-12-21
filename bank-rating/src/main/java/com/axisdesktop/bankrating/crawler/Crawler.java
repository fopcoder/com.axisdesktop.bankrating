package com.axisdesktop.bankrating.crawler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.axisdesktop.bankrating.service.FetchDataService;

public class Crawler {

	@Autowired
	private FetchDataService fetchServise;

	private String url;
	private String host;
	private String path;
	private HashMap<String, Integer> queue = new HashMap<>();

	public Crawler( String url ) throws URISyntaxException {
		URI uri = new URI( url );

		this.url = url;
		this.host = uri.getHost();
		this.path = uri.getPath();

		// fetchServise.add();
		queue.put( url, 0 );
	}

	public void start() {
		int size = 0;
		while( ( size = queue.size() ) > 0 ) {
			try {
				System.out.println( size );
				Thread.sleep( 1000 );
				queue.put( "k" + size, size );
			}
			catch( InterruptedException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// new Worker( this, url );
	}
}
