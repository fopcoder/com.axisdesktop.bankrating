package com.axisdesktop.bankrating.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.axisdesktop.bankrating.crawler.Crawler;

@Configuration
@ComponentScan( "com.axisdesktop.bankrating" )
@PropertySource( "classpath:application.properties" )
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public Crawler crawler() {
		return new Crawler();
	}

}
