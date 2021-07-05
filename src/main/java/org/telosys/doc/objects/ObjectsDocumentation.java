package org.telosys.doc.objects;

import org.telosys.doc.DocVersion;
import org.telosys.doc.commons.DestinationFolder;
import org.telosys.doc.commons.Logger;
import org.telosys.doc.objects.tooling.ObjectsDocGenerator;

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
		String destDir = DestinationFolder.getFullDestinationDir(DOC_DIR_IN_PROJECT);
		int n = ObjectsDocGenerator.generateHtmlDoc(destDir);		
		Logger.log("End of objects doc generation. " + n + " files generated.");
	}
	
	//------------------------------------------------------------------------------
}
