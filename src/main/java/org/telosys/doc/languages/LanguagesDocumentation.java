package org.telosys.doc.languages;

import org.telosys.doc.languages.tooling.LanguagesDocGenerator;
import org.telosys.tools.commons.DirUtil;

public class LanguagesDocumentation {

	
	/**
	 * Current project's documentation directory (where to generate)
	 */
	private static final String DOC_DIR_IN_PROJECT = "/html/languages" ;
	
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
		
		LanguagesDocGenerator.generateHtmlDoc(destDir);
	}
	
	//------------------------------------------------------------------------------
}
