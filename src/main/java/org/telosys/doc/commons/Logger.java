package org.telosys.doc.commons;

public class Logger {

	private Logger() { }
	
	public static final void log(String msg) {
		System.out.println( msg );
	}
}
