package org.telosys.doc.languages.tooling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.telosys.doc.commons.DestinationFolder;
import org.telosys.doc.commons.ItemLink;
import org.telosys.doc.commons.Logger;
import org.telosys.doc.languages.LanguagesList;
import org.telosys.tools.commons.FileUtil;
import org.telosys.tools.generic.model.types.LiteralValuesProvider;
import org.telosys.tools.generic.model.types.TypeConverter;


public class LanguagesDocGenerator {

	private static final String TOC_FILENAME   = "doc-languages.html" ;
	private static final String INDEX_FILENAME = "index.html" ;

	/**
	 * Private constructor
	 */
	private LanguagesDocGenerator () {
	}
	
	/**
	 * Generates Telosys target languages documentation in the given directory
	 * @param destDir destination directory
	 * @return
	 */
	public static int generateHtmlDoc(String destDir) {
		log( "Languages documentation generation" );
		log( "Languages doc - STEP 1 : preparation" );
		File destination = DestinationFolder.prepare(destDir);

		// Generate all the documentation pages
		log( "Languages doc - STEP 2 : pages generation" );
		int n = generatePages(destDir);
		
		// Generate the Table Of Contents 
		log( "Languages doc - STEP 3 : table of content generation" );
		String tocFullFileName = FileUtil.buildFilePath(destination.getParent(), TOC_FILENAME);
		generateTOC(tocFullFileName);
		
		// Generate the index file in the same directory
		log( "Languages doc - STEP 4 : index generation" );
		String indexFullFileName = FileUtil.buildFilePath(destination.getPath(), INDEX_FILENAME);
		generateTOC(indexFullFileName);
		
		return n;
	}
	
	/**
	 * Generates all documentation pages (1 page per language)
	 * @param destDir
	 * @return
	 */
	private static int generatePages(String destDir) { 
		int n = 0 ;
		List<LanguageDocumenter> list = LanguagesList.getLanguages();
		for ( LanguageDocumenter ld : list ) {
			String htmlFileName = buildHtmlFileName(destDir, ld.getLanguageName());
			generatePageFile(htmlFileName, ld.getTypeConverter(), ld.getLiteralValuesProvider());
			n++;
		}
		return n;
	}
	
	/**
	 * Generates Table Of Content file (TOC or index)
	 * @param tocFullFileName
	 */
	private static void generateTOC(String tocFullFileName) {
		log( "File : " + tocFullFileName);
		List<ItemLink> itemLinks = new LinkedList<>();
		for ( LanguageDocumenter ld : LanguagesList.getLanguages() ) {
			itemLinks.add( new ItemLink(ld.getLinkText(), ld.getHtmlPage()) ); 
		}

		LanguagesTOCGeneratorHTML tocGenerator = new LanguagesTOCGeneratorHTML(tocFullFileName, itemLinks);
		tocGenerator.generateTOCFile();
	}
	
	private static String buildHtmlFileName(String destDir, String languageName) {
		return destDir + "/language-" + languageName + ".html" ;
	}
	
	private static void generatePageFile(String fileName, TypeConverter typeConverter, LiteralValuesProvider literalValuesProvider ) {
		
		Logger.log("Print doc : " + fileName );
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			
			LanguageHtmlPrinter htmlPrinter = new LanguageHtmlPrinter( typeConverter, literalValuesProvider, writer);		
			htmlPrinter.printDoc();
			
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void log(String msg) {
		Logger.log(msg);
	}

}
