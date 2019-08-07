package org.telosys.doc;

import org.telosys.doc.languages.LanguagesDocumentation;
import org.telosys.doc.objects.ObjectsDocumentation;

public class AllDocumentation {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("GENERATING OBJECTS DOCUMENTATION...");
		ObjectsDocumentation.genDoc();
		System.out.println("-------------------------------------");
		
		System.out.println("GENERATING LANGUAGES DOCUMENTATION...");
		LanguagesDocumentation.genDoc();
		System.out.println("-------------------------------------");
		
	}
	
	//------------------------------------------------------------------------------
}
