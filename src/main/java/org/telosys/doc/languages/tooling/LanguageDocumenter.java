package org.telosys.doc.languages.tooling;

import org.telosys.tools.generic.model.types.LiteralValuesProvider;
import org.telosys.tools.generic.model.types.TypeConverter;

public class LanguageDocumenter {

	private final String languageName ;
	private final TypeConverter typeConverter ;
	private final LiteralValuesProvider literalValuesProvider ;
	
	/**
	 * Constructor
	 * @param languageName
	 * @param typeConverter
	 * @param literalValuesProvider
	 */
	public LanguageDocumenter(String languageName,
			TypeConverter typeConverter,
			LiteralValuesProvider literalValuesProvider) {
		super();
		this.languageName = languageName;
		this.typeConverter = typeConverter;
		this.literalValuesProvider = literalValuesProvider;
	}

	public String getLanguageName() {
		return languageName;
	}

	public TypeConverter getTypeConverter() {
		return typeConverter;
	}

	public LiteralValuesProvider getLiteralValuesProvider() {
		return literalValuesProvider;
	}
	
} 
