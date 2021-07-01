package org.telosys.doc.objects;

import org.telosys.doc.DocVersion;
import org.telosys.doc.commons.Logger;
import org.telosys.doc.objects.tooling.ObjectsDocGenerator;
import org.telosys.tools.commons.DirUtil;

public class ObjectsDocumentation {

	
	/**
	 * Current project's documentation directory (where to generate)
	 */
	private static final String DOC_DIR_IN_PROJECT = DocVersion.DOC_VERSION_DIR + "/objects" ;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		genDoc();
	}
	
	public static void genDoc() {
		Logger.log("Objects doc generation... ");
		String userDir = DirUtil.getUserWorkingDirectory(); // retrieved from the "user.dir" system property
		Logger.log( "USER DIR : " + userDir ); 	// USER DIR is "X:\xxx\xxx\workspace\project"
		String destDir = userDir + DOC_DIR_IN_PROJECT ;
		Logger.log( "DEST DIR : " + destDir );	
		
		int n = ObjectsDocGenerator.generateHtmlDoc(destDir);
		
		Logger.log("End of objects doc generation. " + n + " files generated.");
	}
	
	//------------------------------------------------------------------------------
}
