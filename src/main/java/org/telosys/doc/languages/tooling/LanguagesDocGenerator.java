package org.telosys.doc.languages.tooling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.telosys.doc.commons.DestinationFolder;
import org.telosys.doc.languages.LanguagesList;
import org.telosys.tools.commons.FileUtil;
import org.telosys.tools.generic.model.types.LiteralValuesProvider;
import org.telosys.tools.generic.model.types.TypeConverter;


public class LanguagesDocGenerator {

	private static final String TOC_FILENAME = "doc-languages.html" ;
	
	/**
	 * Private constructor
	 */
	private LanguagesDocGenerator () {
	}
	
	/**
	 * Generates the documentation files in the given directory
	 * @param destDir destination directory
	 * @return
	 */
	public static void generateHtmlDoc(String destDir) {
		System.out.println( "Destination directory : " + destDir );	
		
		System.out.println("Starting code generation...");
		int n = generateAllHtmlFiles(destDir);
		
		System.out.println("Normal end of generation : " + n + " files generated.");
	
	}
	
	private static int generateAllHtmlFiles(String destDir) {
		File destination = DestinationFolder.prepare(destDir);

		// Generate all the documentation pages
		int n = 0 ;
		List<LanguageDocumenter> list = LanguagesList.getLanguages();
		for ( LanguageDocumenter ld : list ) {
			String htmlFileName = buildHtmlFileName(destDir, ld.getLanguageName());
			generatedHtmlFile(htmlFileName, ld.getTypeConverter(), ld.getLiteralValuesProvider());
			n++;
		}
		
		// Generate the Table Of Contents 
		String tocFullFileName = FileUtil.buildFilePath(destination.getParent(), TOC_FILENAME);
		LanguagesTOCGeneratorHTML tocGenerator = new LanguagesTOCGeneratorHTML(tocFullFileName);
		tocGenerator.generateTOCFile();

		return n;
	}
	
	private static String buildHtmlFileName(String destDir, String languageName) {
		return destDir + "/language-" + languageName + ".html" ;
	}
	
	private static void generatedHtmlFile(String fileName, TypeConverter typeConverter, LiteralValuesProvider literalValuesProvider ) {
		
		System.out.println("Print doc : " + fileName );
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			
			LanguageHtmlPrinter tcd = new LanguageHtmlPrinter( typeConverter, literalValuesProvider, writer);		
			tcd.printDoc();
			
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
