package com.axisdesktop.bankrating.crawler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;

import com.axisdesktop.bankrating.crawler.impl.MinfinParser;
import com.axisdesktop.bankrating.entity.FetchData;
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

		try {

			FetchData fd = fetchServise.getByUrl( this.url );

			if( fd == null ) {
				fd = new FetchData( url, 1 );
				fetchServise.save( fd );
			}

			System.out.println( fd );
			if( true ) return;

			String t = Jsoup.connect( this.url ).get().html();

			Parser p = new MinfinParser( t ).parse();
			Map<String, String> map = p.paging();

			for( String d : map.keySet() ) {
				for( String l : p.links() ) {
					l += "?data=" + d;
					System.out.println( l );
					Thread.sleep( 2000 );
				}
			}

			// System.out.println( t );
		}
		catch( Exception e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Elements links = doc.select( "a[href]" );
		//
		// for( Element link : links ) {
		// String nl = link.attr( "abs:href" );
		//
		// if( nl.contains( "axisdesktop.com" ) && !nl.contains( "go.ax" ) ) {
		// if( this.cr.getQueue().add( nl ) ) {
		// // System.out.println( nl );
		// }
		// }
		// }
		//
		// this.cr.getQueue().update( uri, Status.FINISHED );
		//
		// for( QueryItem s : this.cr.getQueue().list( Status.NEW ) ) {
		// Thread.sleep( 1_000 );
		// this.cr.getExecutor().execute( new WorkerImpl( s, cr ) );
		// }

		// int size = 0;
		// while( ( size = queue.size() ) > 0 ) {
		// try {
		// System.out.println( size );
		// Thread.sleep( 1000 );
		// queue.put( "k" + size, size );
		// }
		// catch( InterruptedException e ) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		// new Worker( this, url );
	}
}
