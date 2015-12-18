package com.axisdesktop.bankrating.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.axisdesktop.bankrating.crawler.MinfinParser;
import com.axisdesktop.bankrating.crawler.Parser;

public class MinfinParserTest {
	public static String indexHtml;
	public static String bankHtml;

	// @Rule
	// public final ExpectedException thrown = ExpectedException.none();

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
		assertNotNull( "new MinfinParser( indexHtml, bankHtml ) should not be null", new MinfinParser( indexHtml,
				bankHtml ) );
		assertNotNull( "new MinfinParser( indexHtml ) should not be null", new MinfinParser( indexHtml ) );
		assertNotNull( "new MinfinParser(  bankHtml ) should not be null", new MinfinParser( bankHtml ) );
	}

	@Test
	public void shouldMinfinParserImplementsParser() {
		assertTrue( "new MinfinParser(  bankHtml ) should implements Parser",
				new MinfinParser( bankHtml ) instanceof Parser );
	}

	@Test
	public void shouldMinfinParserIndexParse() {
		Parser parser = new MinfinParser( indexHtml ).parse();

		assertThat( "links = 37", parser.links().size(), is( 37 ) );
		assertThat( "paging = 12", parser.paging().size(), is( 12 ) );
		assertThat( "data = 37", parser.data().size(), is( 37 ) );
	}
}
