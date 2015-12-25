package com.axisdesktop.bankrating;

import java.net.URISyntaxException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.axisdesktop.bankrating.config.AppInitializer;
import com.axisdesktop.bankrating.crawler.Crawler;

@Component
@Configuration
public class Main {

	public static void main( String[] args ) throws URISyntaxException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext( AppInitializer.class );

		Crawler crawler = ctx.getBean( Crawler.class );
		// crawler.
		// Crawler crawler = new Crawler( "http://minfin.com.ua/banks/rating/" );

		// crawler.start();
		// System.out.println( "fuuuuuuuu" );
	}
}
