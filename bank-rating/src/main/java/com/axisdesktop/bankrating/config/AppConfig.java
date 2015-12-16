package com.axisdesktop.bankrating.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan( "com.axisdesktop.bankrating" )
@PropertySource( "classpath:application.properties" )
public class AppConfig extends WebMvcConfigurerAdapter {
	// @Autowired
	// private Environment env;

}
