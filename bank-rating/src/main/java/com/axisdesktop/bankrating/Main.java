package com.axisdesktop.bankrating;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.axisdesktop.bankrating.config.AppConfig;
import com.axisdesktop.bankrating.config.PersistenceConfig;
import com.axisdesktop.bankrating.crawler.Crawler;

public class Main {

	public static void main( String[] args ) throws URISyntaxException, IOException {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext( AppConfig.class,
				PersistenceConfig.class );

		Crawler crawler = ctx.getBean( Crawler.class );
		crawler.setUrl( "http://minfin.com.ua/banks/rating/" );

		crawler.start();

		ctx.close();
	}
}
