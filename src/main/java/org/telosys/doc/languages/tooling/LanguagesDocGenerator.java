package org.telosys.doc.languages.tooling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.telosys.tools.commons.DirUtil;
import org.telosys.tools.generic.model.types.LiteralValuesProvider;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForCSharp;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForGo;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForJava;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForJavaScript;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForPHP;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForPython;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForTypeScript;
import org.telosys.tools.generic.model.types.TypeConverter;
import org.telosys.tools.generic.model.types.TypeConverterForCSharp;
import org.telosys.tools.generic.model.types.TypeConverterForGo;
import org.telosys.tools.generic.model.types.TypeConverterForJava;
import org.telosys.tools.generic.model.types.TypeConverterForJavaScript;
import org.telosys.tools.generic.model.types.TypeConverterForPHP;
import org.telosys.tools.generic.model.types.TypeConverterForPython;
import org.telosys.tools.generic.model.types.TypeConverterForTypeScript;


public class LanguagesDocGenerator {

	/**
	 * List of all the supported languages with their TypeConverter and LiteralValuesProvider
	 */
	private static LinkedList<LanguageDocumenter> list = new LinkedList<>();
	static {
		list.add(new LanguageDocumenter("csharp",     new TypeConverterForCSharp(),     new LiteralValuesProviderForCSharp()) );
		list.add(new LanguageDocumenter("go",         new TypeConverterForGo(),         new LiteralValuesProviderForGo()) );
		list.add(new LanguageDocumenter("java",       new TypeConverterForJava(),       new LiteralValuesProviderForJava()) );
		list.add(new LanguageDocumenter("javascript", new TypeConverterForJavaScript(), new LiteralValuesProviderForJavaScript()) );
		list.add(new LanguageDocumenter("php",        new TypeConverterForPHP(),        new LiteralValuesProviderForPHP()) );
		list.add(new LanguageDocumenter("python",     new TypeConverterForPython(),     new LiteralValuesProviderForPython()) );
		list.add(new LanguageDocumenter("typescript", new TypeConverterForTypeScript(), new LiteralValuesProviderForTypeScript()) );
	}
	
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
		return destDir + "/lang-types-" + languageName + ".html" ;
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
