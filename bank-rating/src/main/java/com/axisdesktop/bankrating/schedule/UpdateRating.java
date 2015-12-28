package com.axisdesktop.bankrating.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.axisdesktop.bankrating.crawler.Crawler;

@Component
public class UpdateRating {

	@Autowired
	private Crawler crawler;

	@Scheduled( cron = "0 */5 * * * *" )
	public void updateRateing() {
		crawler.setUrl( "http://minfin.com.ua/banks/rating/" );
		crawler.start();
	}
}
