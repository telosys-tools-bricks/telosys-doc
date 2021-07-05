package org.telosys.doc.languages;

import org.telosys.doc.DocVersion;
import org.telosys.doc.commons.DestinationFolder;
import org.telosys.doc.commons.Logger;
import org.telosys.doc.languages.tooling.LanguagesDocGenerator;

public class LanguagesDocumentation {

	/**
	 * Current project's documentation directory (where to generate)
	 */
	private static final String DOC_DIR_IN_PROJECT = DocVersion.DOC_VERSION_DIR + "/languages" ;

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		genDoc();
	}
	
	public static void genDoc() {
		Logger.log("Languages doc generation... ");
		String destDir = DestinationFolder.getFullDestinationDir(DOC_DIR_IN_PROJECT);
		int n = LanguagesDocGenerator.generateHtmlDoc(destDir);
		Logger.log("End of languages doc generation. " + n + " files generated.");
	}

}
