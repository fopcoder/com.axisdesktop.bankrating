package com.axisdesktop.bankrating.crawler;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.axisdesktop.bankrating.crawler.impl.MinfinParser;
import com.axisdesktop.bankrating.entity.Bank;
import com.axisdesktop.bankrating.entity.FetchData;
import com.axisdesktop.bankrating.entity.RatingMinfin;
import com.axisdesktop.bankrating.service.FetchDataService;

public class Crawler {

	@Autowired
	private FetchDataService fetchServise;

	private String url;

	public Crawler() {
	}

	public Crawler( String url ) {
		this.url = url;
	}

	public void start() {

		try {
			// get root url for data
			Fetcher fetcher = new Fetcher( this.url ).fetch();
			String htmlData = fetcher.asString();
			fetcher.clean();

			Thread.sleep( 2000 );

			Parser p = new MinfinParser( htmlData ).parse();
			Map<String, String> dates = p.paging();

			for( String date : dates.keySet() ) {
				String dateUrl = this.url + "?date=" + date;
				System.out.println( "====> " + dateUrl );

				FetchData fd = fetchServise.getDataByUrl( dateUrl );

				if( fd != null ) continue;

				fetcher = new Fetcher( dateUrl ).fetch();
				htmlData = fetcher.asString();
				fetcher.clean();

				Thread.sleep( 2000 );

				if( htmlData == null ) continue;

				p = new MinfinParser( htmlData ).parse();

				Map<String, Map<String, String>> indexData = p.data();
				System.out.println( indexData );

				for( Map<String, String> bankRow : indexData.values() ) {
					Bank bank = new Bank( bankRow );
					bank = fetchServise.saveBank( bank );

					RatingMinfin rating = new RatingMinfin( bank, bankRow, date );
					bank.getRatings().add( rating );

				}

				// fd = new FetchData( dateUrl, 1 );
				fetchServise.saveData( new FetchData( dateUrl, 1 ) );

				// String t = Jsoup.connect( this.url ).get().html();

				// for( String l : p.links() ) {
				// l += "?date=" + d;
				//
				// fd = new FetchData( l, 1 );
				// fetchServise.save( fd );
				//
				// System.out.println( fetchServise.count() );
				// System.out.println( l );
				// Thread.sleep( 1000 );
				// }
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

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
	}
}
