package com.axisdesktop.bankrating.crawler;

import java.util.Map;
import java.util.Set;

public interface Parser {
	Parser parse();

	<V, K> Map<K, V> data();

	Set<String> links();

	<V, K> Map<K, V> paging();

}
