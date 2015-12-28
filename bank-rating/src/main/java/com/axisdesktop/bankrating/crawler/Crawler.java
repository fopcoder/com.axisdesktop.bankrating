package com.axisdesktop.bankrating.crawler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.axisdesktop.bankrating.crawler.impl.MinfinParser;
import com.axisdesktop.bankrating.entity.Bank;
import com.axisdesktop.bankrating.entity.FetchData;
import com.axisdesktop.bankrating.entity.Rating;
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
				Rating rating = null;
				for( Map<String, String> bankRow : indexData.values() ) {
					try {
						Bank bank = new Bank( bankRow );
						bank = fetchServise.saveBank( bank );

						rating = new Rating( bank, bankRow, date );
						fetchServise.saveRating( rating );

						// bank.getRatings().add( rating );
					}
					catch( Exception e ) {
						System.out.println( rating );
					}
				}

				fetchServise.saveData( new FetchData( dateUrl, 1 ) );
			}

		}
		catch( Exception e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
	}
}
