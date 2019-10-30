package org.telosys.doc.commons;

import java.io.File;

public class DestinationFolder {

	private DestinationFolder() {
	}
	
	public static File prepare(String destDir) {
		System.out.println( "Destination directory : " + destDir );
		File fileDir = new File(destDir);
		if ( ! fileDir.exists() ) {
		    System.out.println("Creating directory : " + destDir);
		    if( fileDir.mkdirs() ) {    
		    	System.out.println("Created");
		    }
		    else {
		    	throw new RuntimeException("Cannot create directory '" + destDir + "'");
		    }
		}
    	return fileDir ;
	}

}
