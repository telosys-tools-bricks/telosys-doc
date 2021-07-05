package org.telosys.doc.commons;

import java.io.File;

import org.telosys.tools.commons.DirUtil;

public class DestinationFolder {

	/**
	 * Private constructor
	 */
	private DestinationFolder() {
	}
	
	public static String getFullDestinationDir(String subDir) {
		String userDir = DirUtil.getUserWorkingDirectory(); // retrieved from the "user.dir" system property
		Logger.log( "USER DIR : " + userDir ); 	// USER DIR is "X:\xxx\xxx\workspace\project"
		String destDir = userDir + subDir ;
		Logger.log( "DEST DIR : " + destDir );
		return destDir;
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
