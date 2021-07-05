package org.telosys.doc;

import org.telosys.doc.commons.Logger;
import org.telosys.doc.index.IndexGeneratorHTML;
import org.telosys.doc.languages.LanguagesDocumentation;
import org.telosys.doc.objects.ObjectsDocumentation;

public class AllDocumentation {

	private static final String SEPARATOR = "----------------------------------------" ;
	
	/**
	 * Main entry point to generate all the documentation for the current version 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Logger.log("DOCUMENTATION VERSION   : " + DocVersion.DOC_VERSION);
		Logger.log("DOCUMENTATION DIRECTORY : " + DocVersion.DOC_VERSION_DIR);

		Logger.log(SEPARATOR);
		Logger.log("GENERATING OBJECTS DOCUMENTATION...");
		ObjectsDocumentation.genDoc();
		Logger.log(SEPARATOR);
		Logger.log("GENERATING LANGUAGES DOCUMENTATION...");
		LanguagesDocumentation.genDoc();
		Logger.log(SEPARATOR);
		Logger.log("GENERATING INDEX DOCUMENTATION...");		
		IndexGeneratorHTML.genDoc();
		Logger.log(SEPARATOR);
	}
}
