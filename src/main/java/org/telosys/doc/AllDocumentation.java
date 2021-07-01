package org.telosys.doc;

import org.telosys.doc.commons.Logger;
import org.telosys.doc.languages.LanguagesDocumentation;
import org.telosys.doc.objects.ObjectsDocumentation;

public class AllDocumentation {

	/**
	 * Main entry point to generate all the documetation
	 * @param args
	 */
	public static void main(String[] args) {
		
		Logger.log("GENERATING OBJECTS DOCUMENTATION...");
		ObjectsDocumentation.genDoc();
		Logger.log("-------------------------------------");
		
		Logger.log("GENERATING LANGUAGES DOCUMENTATION...");
		LanguagesDocumentation.genDoc();
		Logger.log("-------------------------------------");
		
	}
}
