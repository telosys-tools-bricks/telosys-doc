/**
 *  Copyright (C) 2008-2017  Telosys project org. ( http://www.telosys.org/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.telosys.doc.languages;

import java.util.LinkedList;
import java.util.List;

import org.telosys.doc.languages.tooling.LanguageDocumenter;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForCPlusPlus;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForCSharp;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForGo;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForJava;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForJavaScript;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForKotlin;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForPHP;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForPython;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForScala;
import org.telosys.tools.generator.languages.literals.LiteralValuesProviderForTypeScript;
import org.telosys.tools.generator.languages.types.TypeConverterForCPlusPlus;
import org.telosys.tools.generator.languages.types.TypeConverterForCSharp;
import org.telosys.tools.generator.languages.types.TypeConverterForGo;
import org.telosys.tools.generator.languages.types.TypeConverterForJava;
import org.telosys.tools.generator.languages.types.TypeConverterForJavaScript;
import org.telosys.tools.generator.languages.types.TypeConverterForKotlin;
import org.telosys.tools.generator.languages.types.TypeConverterForPHP;
import org.telosys.tools.generator.languages.types.TypeConverterForPython;
import org.telosys.tools.generator.languages.types.TypeConverterForScala;
import org.telosys.tools.generator.languages.types.TypeConverterForTypeScript;

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
		
		list.add(new LanguageDocumenter("kotlin", "Kotlin",           
				new TypeConverterForKotlin(),     new LiteralValuesProviderForKotlin()) );

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
