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
package org.telosys.doc.languages.tooling;

import org.telosys.tools.generator.context.EnvInContext;
import org.telosys.tools.generator.languages.literals.LiteralValuesProvider;
import org.telosys.tools.generator.languages.types.TypeConverter;

public class LanguageDocumenter {

	private final String languageName ;
	private final String linkText ;

	private final TypeConverter typeConverter ;
	private final LiteralValuesProvider literalValuesProvider ;
	
	/**
	 * Constructor
	 * @param languageName
	 * @param linkText
	 * @param typeConverter
	 * @param literalValuesProvider
	 */
	public LanguageDocumenter(String languageName,
			String linkText, 
			TypeConverter typeConverter,
			LiteralValuesProvider literalValuesProvider) {
		super();
		this.languageName = languageName;
		this.linkText = linkText;
		this.typeConverter = typeConverter;
		this.literalValuesProvider = literalValuesProvider;
		
		// Set $env for typeWithNullableMark ( typeWithNullableMark = true by default in $env )
		this.typeConverter.setEnv(new EnvInContext());
	}

	public String getLanguageName() {
		return languageName;
	}

	public String getLinkText() {
		return linkText;
	}

	public String getHtmlPage() {
		return "language-" + languageName ;
	}

	public TypeConverter getTypeConverter() {
		return typeConverter;
	}

	public LiteralValuesProvider getLiteralValuesProvider() {
		return literalValuesProvider;
	}
	
} 
