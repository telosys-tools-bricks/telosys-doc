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

import java.io.BufferedWriter;
import java.util.LinkedList;
import java.util.List;

import org.telosys.tools.generic.model.types.AttributeTypeInfo;
import org.telosys.tools.generic.model.types.LanguageType;
import org.telosys.tools.generic.model.types.LiteralValue;
import org.telosys.tools.generic.model.types.LiteralValuesProvider;
import org.telosys.tools.generic.model.types.NeutralType;
import org.telosys.tools.generic.model.types.TypeConverter;

public class LanguageHtmlPrinter extends CommonHtmlPrinter {

	private final TypeConverter          typeConverter ;
	private final LiteralValuesProvider  literalValuesProvider ;
	
	protected LanguageHtmlPrinter(TypeConverter typeConverter, LiteralValuesProvider literalValuesProvider, BufferedWriter writer ) {
		super(writer);
		this.typeConverter = typeConverter ;
		this.literalValuesProvider = literalValuesProvider ;
	}
	
	protected void printDoc() {
		String title = "\"" + typeConverter.getLanguageName() + "\" language specificities"; 
		printHtmlHeader(title);
		print("<hr/>");		
		printIntro();
		print("<hr/>");		
		printTypeConversion();
		printTypesRemarks();
		print("<hr/>");
		printLiteralTrueFalseNull();
		print("<hr/>");
		printLiteralValuesTable();
		printHtmlFooter();
	}
	
	private void printIntro() {
		println("<br>");

		print("<p>" );	
		if ( "Java".equalsIgnoreCase(typeConverter.getLanguageName())) {
			print("Java is the default target language <br>" );	
			print("therefore it is <b>not necessary</b> to call  <b>'#set( $env.language = 'Java' )'</b> in the templates files." );	
		} 
		else {
			print("To define <b>" + typeConverter.getLanguageName() + "</b> as the target language call &nbsp;" );	
			print("<span class=\"code\">#set( $env.language = '" + typeConverter.getLanguageName() + "' )</span>&nbsp; in the templates files." );
		}
		println("<br>");
		println("<br>");
		print("The information below shows the behavior of the generator when '" + typeConverter.getLanguageName() 
				+ "' is the current target language.");
		print("</p>" );	
		println("<br>");		
	}
	
	private void printTypeConversion() {
		println();
		println("<h2>Type conversion ( model type to language type ) </h2>" );			
		println();
		print("The language type conversion has an impact on :" );	
		print("<ul class=\"code\" >" );	
		print("<li> $attribute.type </li>" );
		print("<li> $attribute.fullType </li>" );
		print("<li> $attribute.simpleType </li>" );
		print("<li> $attribute.wrapperType </li>" );
		print("<li> $attribute.isPrimitiveType() </li>" );
		print("</ul>" );	
		println();
		
		print("<table style=\"\"> ");
		println();
		println("<colgroup>"); // 1 col GhostWhite + 5 col standard
//		print(" <col style=\"width: 16%; background-color: GhostWhite ; \">" );
//		for ( int n=0 ; n < 5 ; n++ ) { // 5 columns x 16% = 80%
//			print(" <col style=\"width: 14%;\">" );
//		}
		print(" <col style=\"background-color: GhostWhite ; \">" );
		for ( int n=0 ; n < 5 ; n++ ) { // 5 columns x 16% = 80%
			print(" <col>" );
		}
		println();
		println("</colgroup>");

		println("<thead>");
		print("<tr>");
		print(" <th> Model type &nbsp; &nbsp;</th>" ); 
		print(" <th> Default &nbsp; &nbsp; &nbsp;&nbsp;</th> " );
		print(" <th> @UnsignedType </th>" ); 
		print(" <th> @NotNull &nbsp; &nbsp; &nbsp;</th>" ); 
		print(" <th> @PrimitiveType</th>" ); 
		print(" <th> @ObjectType &nbsp;&nbsp;</th>" );  
		println("</tr>");
	    println("</thead>");

		println("<tbody>");
		for ( String neutralType : NeutralType.getAllNeutralTypes() ) {
			List<String> types = getSimpleTypes(neutralType); 
			printTypeRow(neutralType, types);
		}		
		println("</tbody>");
		println("</table>");
	}
	
	private List<String> getSimpleTypes(String neutralType) {
		List<String> list = new LinkedList<>();
		
		list.add( getSimpleType(neutralType, AttributeTypeInfo.NONE) ); // "default"
		list.add( getSimpleType(neutralType, AttributeTypeInfo.UNSIGNED_TYPE) );
		list.add( getSimpleType(neutralType, AttributeTypeInfo.NOT_NULL) );
		list.add( getSimpleType(neutralType, AttributeTypeInfo.PRIMITIVE_TYPE) );
		list.add( getSimpleType(neutralType, AttributeTypeInfo.OBJECT_TYPE) );
		
		return list;
	}
	
	private LanguageType getLanguageType(String neutralType, int info) {
		return typeConverter.getType(new AttributeTypeInfo(neutralType, info));
	}	
	
	private String getSimpleType(String neutralType, int info) {
		LanguageType lt = getLanguageType(neutralType, info);
		return lt.getSimpleType();
	}	
	
	private void printTypeRow(String neutralType, List<String> types) {
				
		print(" <tr>" );
		print(" <td>" + neutralType + "</td>");
		for ( String type : types ) {
			print(" <td>" + type + "</td>");
		}
		print(" </tr>\n" );
	}

	private void printTypesRemarks() {
		List<String >remarks = typeConverter.getComments() ;
		if ( remarks == null || remarks.isEmpty() ) {
			return ;
		}
		print("<h2>Remarks</h2>" );	
		print("<p class=\"code\" >" );	
		for ( String s : typeConverter.getComments() ) {
			print( s + "<br>" );	
		}
		print("</p>" );	
		println("<br>");		
	}

	//---------------------------------------------------------------------------------
	/**
	 * Returns all types possibilities for the current language (grouped by neutral type) <br>
	 * e.g. "String", "int", "Integer" etc
	 * @return
	 */
	private List<LanguageType> getAllLanguageTypes() {
		List<LanguageType> list = new LinkedList<>();
		for ( String neutralType : NeutralType.getAllNeutralTypes() ) {
			getLanguageTypesForNeutralType(list, neutralType);
		}		
		return list;
	}
	private void getLanguageTypesForNeutralType(List<LanguageType> list, String neutralType) {
		// Add at least the default type
		addIfNotInList( list, getLanguageType(neutralType, AttributeTypeInfo.NONE ) ) ; // "default type"
		// Add other types 
		addIfNotInList( list, getLanguageType(neutralType, AttributeTypeInfo.UNSIGNED_TYPE ) ) ;
		addIfNotInList( list, getLanguageType(neutralType, AttributeTypeInfo.NOT_NULL ) ) ;
		addIfNotInList( list, getLanguageType(neutralType, AttributeTypeInfo.PRIMITIVE_TYPE ) ) ;
		addIfNotInList( list, getLanguageType(neutralType, AttributeTypeInfo.OBJECT_TYPE ) ) ;
	}
	private void addIfNotInList(List<LanguageType> list, LanguageType languageType) {
		if ( ! list.contains(languageType) ) {
			list.add(languageType);
		}
	}
	
	private void printLiteralTrueFalseNull() {
		println();
		println("<h2>Literals for TRUE, FALSE and NULL </h2>" );	
		println("<table style=\"\"> ");
		println("<colgroup>");
		print(" <col style=\"background-color: GhostWhite ; \">" );
		print(" <col>" );
		println();
		println("</colgroup>");
		println("<tbody>");
		print(" <tr> <td> TRUE  </td> <td>" + literalValuesProvider.getLiteralTrue() + "</td> </tr>");
		print(" <tr> <td> FALSE </td> <td>" + literalValuesProvider.getLiteralFalse() + "</td> </tr>");
		print(" <tr> <td> NULL  </td> <td>" + literalValuesProvider.getLiteralNull() + "</td> </tr>");
		println("</tbody>");
		println("</table>");
		println("<br>");
	}

	private void printLiteralValuesTable() {
		println();
		println("<h2>Literal values </h2>" );	
		println("<table style=\"\"> ");
		println("<colgroup>");
		print(" <col style=\"background-color: GhostWhite ; \">" );
		print(" <col>" );
		print(" <col>" );
		print(" <col>" );
		println();
		println("</colgroup>");

		println("<thead>");
		print(" <tr>");
		print(" <th> Model type </th>" ); 
		print(" <th> Language type </th> " );
		print(" <th> Language full type </th> " );
		print(" <th> Language literal value example </th>" ); 
		println("</tr>");
	    println("</thead>");

		println("<tbody>");
		for ( LanguageType languageType : getAllLanguageTypes() ) {
			LiteralValue literalValue = literalValuesProvider.generateLiteralValue(languageType, 3, 1);
			printLiteralValueRow(languageType, literalValue.getCurrentLanguageValue());
		}		
		println("</tbody>");
		println("</table>");
		println("<br>");
	}

	private void printLiteralValueRow(LanguageType languageType, String literalValue) {
		
		print(" <tr>" );
		print(" <td>" + languageType.getNeutralType() + "</td>");
		print(" <td>" + languageType.getSimpleType() + "</td>");
		print(" <td>" + languageType.getFullType() + "</td>");
		print(" <td>" + literalValue + "</td>");
		print(" </tr>" );
		println();
	}
	
}
