package com.axisdesktop.bankrating.crawler;

import java.util.Map;

public interface Parser {
	Map<String, String> parse();

	<T> T convert();
}
