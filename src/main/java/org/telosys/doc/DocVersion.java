package org.telosys.doc;

import org.telosys.tools.generator.GeneratorVersion;

public class DocVersion {

	/**
	 * Telosys version used for documentation generation
	 */
	public static final String DOC_VERSION = GeneratorVersion.GENERATOR_VERSION ;

	/**
	 * Documentation directory for the current version
	 * (where to generate in the current project directory)
	 */
	public static final String DOC_VERSION_DIR = "/doc/v" + DOC_VERSION.replace(".", "") ; // "/doc/vXXX"

	private DocVersion() { }
	
}
