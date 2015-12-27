package com.axisdesktop.bankrating;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.axisdesktop.bankrating.config.AppConfig;
import com.axisdesktop.bankrating.config.PersistenceConfig;
import com.axisdesktop.bankrating.crawler.Crawler;

public class Main {

	public static void main( String[] args ) throws URISyntaxException, IOException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext( AppConfig.class, PersistenceConfig.class );

		// Fetcher f = new Fetcher( "http://minfin.com.ua/banks/rating/" );
		// System.out.println( f.fetch().asString() );

		// Crawler crawler = new Crawler( "http://minfin.com.ua/banks/rating/" );
		// System.out.println( ctx.getBean( "ser" ) );
		Crawler crawler = ctx.getBean( Crawler.class );
		crawler.setUrl( "http://minfin.com.ua/banks/rating/" );

		crawler.start();

	}
}
