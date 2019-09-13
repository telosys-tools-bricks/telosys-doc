package org.telosys.doc.objects;

import org.telosys.doc.objects.tooling.ObjectsDocGenerator;
import org.telosys.tools.commons.DirUtil;

public class ObjectsDocumentation {

	
	/**
	 * Current project's documentation directory (where to generate)
	 */
	private static final String DOC_DIR_IN_PROJECT = "/html/objects" ;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		genDoc();
	}
	
	public static void genDoc() {
		String userDir = DirUtil.getUserWorkingDirectory(); // retrieved from the "user.dir" system property
		System.out.println( "USER DIR : " + userDir ); 	// USER DIR is "X:\xxx\xxx\workspace\project"
		String destDir = userDir + DOC_DIR_IN_PROJECT ;
		System.out.println( "DEST DIR : " + destDir );	
		
		int n = ObjectsDocGenerator.generateHtmlDoc(destDir);
		
		System.out.println("Normal end of generation. " + n + " files generated.");
	}
	
	//------------------------------------------------------------------------------
}
