package org.telosys.doc.languages.tooling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.telosys.doc.languages.LanguagesList;
import org.telosys.tools.commons.DirUtil;
import org.telosys.tools.generic.model.types.LiteralValuesProvider;
import org.telosys.tools.generic.model.types.TypeConverter;


public class LanguagesDocGenerator {

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
		if ( DirUtil.isValidDirectory(new File(destDir) ) ) {
			int n = 0 ;
			List<LanguageDocumenter> list = LanguagesList.getLanguages();
			for ( LanguageDocumenter ld : list ) {
				String htmlFileName = buildHtmlFileName(destDir, ld.getLanguageName());
				generatedHtmlFile(htmlFileName, ld.getTypeConverter(), ld.getLiteralValuesProvider());
				n++;
			}
			return n;
		}
		else {
			throw new RuntimeException("Destination folder does not exist : " + destDir );
		}
	}
	
	private static String buildHtmlFileName(String destDir, String languageName) {
		return destDir + "/language-" + languageName + ".html" ;
	}
	
	public static void generatedHtmlFile(String fileName, TypeConverter typeConverter, LiteralValuesProvider literalValuesProvider ) {
		
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
