package com.axisdesktop.bankrating;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.axisdesktop.bankrating.config.AppConfig;
import com.axisdesktop.bankrating.config.PersistenceConfig;
import com.axisdesktop.bankrating.crawler.Crawler;

public class Main {

	private static ApplicationContext ctx;

	public static void main( String[] args ) throws URISyntaxException, IOException {
		ctx = new AnnotationConfigApplicationContext( AppConfig.class, PersistenceConfig.class );

		Crawler crawler = ctx.getBean( Crawler.class );
		crawler.setUrl( "http://minfin.com.ua/banks/rating/" );

		crawler.start();
	}
}
