package org.telosys.doc.commons;

import java.io.File;

public class DestinationFolder {

	private DestinationFolder() {
	}
	
	public static File prepare(String destDir) {
		Logger.log( "Destination directory : " + destDir );
		File fileDir = new File(destDir);
		if ( ! fileDir.exists() ) {
			Logger.log("Creating directory : " + destDir);
		    if( fileDir.mkdirs() ) {    
		    	Logger.log("Created");
		    }
		    else {
		    	throw new RuntimeException("Cannot create directory '" + destDir + "'");
		    }
		}
    	return fileDir ;
	}

}
