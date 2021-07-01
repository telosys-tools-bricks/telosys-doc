package org.telosys.doc.languages.tooling;

import org.telosys.tools.generic.model.types.LiteralValuesProvider;
import org.telosys.tools.generic.model.types.TypeConverter;

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
