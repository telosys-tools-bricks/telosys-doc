package org.telosys.doc.languages;

import java.util.LinkedList;
import java.util.List;

import org.telosys.doc.languages.tooling.LanguageDocumenter;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForCPlusPlus;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForCSharp;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForGo;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForJava;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForJavaScript;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForPHP;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForPython;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForScala;
import org.telosys.tools.generic.model.types.LiteralValuesProviderForTypeScript;
import org.telosys.tools.generic.model.types.TypeConverterForCPlusPlus;
import org.telosys.tools.generic.model.types.TypeConverterForCSharp;
import org.telosys.tools.generic.model.types.TypeConverterForGo;
import org.telosys.tools.generic.model.types.TypeConverterForJava;
import org.telosys.tools.generic.model.types.TypeConverterForJavaScript;
import org.telosys.tools.generic.model.types.TypeConverterForPHP;
import org.telosys.tools.generic.model.types.TypeConverterForPython;
import org.telosys.tools.generic.model.types.TypeConverterForScala;
import org.telosys.tools.generic.model.types.TypeConverterForTypeScript;

public class LanguagesList {

	private LanguagesList() {}

	/**
	 * List of all the supported languages with their TypeConverter and LiteralValuesProvider
	 */
	private static LinkedList<LanguageDocumenter> list = new LinkedList<>();
	static {
		list.add(new LanguageDocumenter("c++",   "C++", 
				new TypeConverterForCPlusPlus(),  new LiteralValuesProviderForCPlusPlus()) );
		
		list.add(new LanguageDocumenter("csharp", "C# (Microsoft CSharp)",
				new TypeConverterForCSharp(),     new LiteralValuesProviderForCSharp()) );
		
		list.add(new LanguageDocumenter("go",     "Go (Golang)",
				new TypeConverterForGo(),         new LiteralValuesProviderForGo()) );
		
		list.add(new LanguageDocumenter("java",   "Java", 
				new TypeConverterForJava(),       new LiteralValuesProviderForJava()) );
		
		list.add(new LanguageDocumenter("javascript",  "JavaScript",  
				new TypeConverterForJavaScript(), new LiteralValuesProviderForJavaScript()) );
		
		list.add(new LanguageDocumenter("php",    "PHP",           
				new TypeConverterForPHP(),        new LiteralValuesProviderForPHP()) );
		
		list.add(new LanguageDocumenter("python",  "Python",       
				new TypeConverterForPython(),     new LiteralValuesProviderForPython()) );
		
		list.add(new LanguageDocumenter("scala",  "Scala",           
				new TypeConverterForScala(),      new LiteralValuesProviderForScala()) );
		
		list.add(new LanguageDocumenter("typescript", "TypeScript",
				new TypeConverterForTypeScript(), new LiteralValuesProviderForTypeScript()) );
	}
	
	public static final List<LanguageDocumenter> getLanguages() {
		return list ;
	}

}
