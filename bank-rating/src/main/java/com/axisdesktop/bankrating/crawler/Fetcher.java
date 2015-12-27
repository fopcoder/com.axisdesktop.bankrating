package com.axisdesktop.bankrating.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Fetcher {
	private String url;
	private Path tmpPath;

	public Fetcher( String url ) {
		if( url == null ) throw new IllegalArgumentException( "fetcher url is null" );

		this.url = url;
	}

	public Fetcher fetch() {
		try {
			this.tmpPath = Files.createTempFile( "fetcher", ".tmpwww" );

			URLConnection con = new URL( this.url ).openConnection(); // random proxy!

			try( InputStream is = con.getInputStream(); ) {
				Files.copy( is, tmpPath, StandardCopyOption.REPLACE_EXISTING );
			}
			catch( IOException e ) {
				Files.delete( this.tmpPath );
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch( IOException e1 ) {
			try {
				Files.delete( this.tmpPath );
			}
			catch( IOException e ) {/* ignore */}
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return this;
	}

	public String asString() {
		String f = null;

		try {
			f = new String( Files.readAllBytes( this.tmpPath ) );
		}
		catch( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return f;
	}

	public void clean() {
		if( this.tmpPath != null ) {
			try {
				Files.deleteIfExists( tmpPath );
			}
			catch( IOException e ) {/* ignore */}
		}
	}
}
