package com.axisdesktop.bankrating.test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.axisdesktop.bankrating.crawler.Parser;
import com.axisdesktop.bankrating.crawler.impl.MinfinParser;

public class MinfinParserTest {
	public static String indexHtml;
	public static String bankHtml;

	@BeforeClass
	public static void setup() throws IOException {
		InputStream is = new ClassPathResource( "/html/minfin_index.html" ).getInputStream();
		BufferedReader br = new BufferedReader( new InputStreamReader( is ) );

		StringBuilder buf = new StringBuilder();
		String line;

		while( ( line = br.readLine() ) != null ) {
			buf.append( line );
		}

		indexHtml = buf.toString();

		is = new ClassPathResource( "/html/minfin_bank.html" ).getInputStream();
		br = new BufferedReader( new InputStreamReader( is ) );

		buf = new StringBuilder();

		while( ( line = br.readLine() ) != null ) {
			buf.append( line );
		}

		bankHtml = buf.toString();

		br.close();
		is.close();
	}

	@AfterClass
	public static void tearDown() {
		indexHtml = null;
		bankHtml = null;
	}

	@Test( expected = NullPointerException.class )
	public void shouldThrowNullPointerException() {
		new MinfinParser( null );
	}

	@Test
	public void shouldMinfinParserIndexBankNotNull() {
		assertThat( "new MinfinParser( indexHtml, bankHtml ) should not be null",
				new MinfinParser( indexHtml, bankHtml ), not( nullValue() ) );
		assertThat( "new MinfinParser( indexHtml ) should not be null", new MinfinParser( indexHtml ),
				not( nullValue() ) );
		assertThat( "new MinfinParser(  bankHtml ) should not be null", new MinfinParser( bankHtml ), not( nullValue() ) );
	}

	@Test
	public void shouldMinfinParserImplementParser() {
		assertThat( "new MinfinParser(  bankHtml ) should implement Parser", new MinfinParser( bankHtml ),
				instanceOf( Parser.class ) );
	}

	@Test
	public void shouldMinfinParserIndexParse() {
		Parser parser = new MinfinParser( indexHtml ).parse();

		assertThat( "links = 37", parser.links().size(), is( 37 ) );
		assertThat( "paging = 12", parser.paging().size(), is( 12 ) );
		assertThat( "data = 37", parser.data().size(), is( 37 ) );
	}

	@Test
	public void shouldMinfinParserBankParse() {
		Parser parser = new MinfinParser( bankHtml ).parse();

		@SuppressWarnings( "unchecked" )
		Map<String, String> data = (Map<String, String>)parser.data().get( "base" );

		assertThat( "detail size = 14", data.size(), is( 14 ) );
		assertThat( "get data", data.get( "Объем кредитного портфеля" ), is( "4850.09" ) );
	}
}
