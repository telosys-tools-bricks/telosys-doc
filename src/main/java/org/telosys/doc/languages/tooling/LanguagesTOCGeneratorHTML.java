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

import java.io.PrintWriter;

import org.telosys.doc.commons.AbstractTOCGeneratorHTML;
import org.telosys.doc.commons.ItemLink;

public class LanguagesTOCGeneratorHTML extends AbstractTOCGeneratorHTML {

	private static final String TITLE = "Telosys target languages";
	
	private static final ItemLink[] SORTED_LINKS = new ItemLink[] {
			new ItemLink("C# (Microsoft CSharp)",  "language-csharp"),
			new ItemLink("Go (Golang)",            "language-go"),
			new ItemLink("Java",                   "language-java"),
			new ItemLink("JavaScript",             "language-javascript"),
			new ItemLink("PHP",                    "language-php"),
			new ItemLink("Python",                 "language-python"),
			new ItemLink("TypeScript",             "language-typescript")
	};
	
	public LanguagesTOCGeneratorHTML(String fullFileName) {
		super(fullFileName, TITLE, SORTED_LINKS);
	}

	@Override
	protected void printTextBeforeList(PrintWriter writer) {
		writer.println( "Telosys is able to generate any type of language. <br>" );
		writer.println( "For the most common languages it is possible to indicate the nature of the target language <br>");
		writer.println( "so as to simplify the writing of templates. <br>");
		writer.println( "<br>" );
		writer.println( "The target language can be specified in the template files (.vm) using the following directive : <br>" );
		writer.println( "<br>" );
		writer.println( "<code class=\"example\"><b>" );
		writer.println( "&nbsp;&nbsp;&nbsp; #set( $env.language = 'LanguageName' )" );
		writer.println( "</b></code><br>" );
		writer.println( "<br>" );
		writer.println( "Thus Telosys will be able to apply certain default behaviors, <br>" );
		writer.println( "for example to convert the 'neutral type' of the model into a language-specific type <br>" );
		writer.println( "or to determine literal values adapted to the language. <br>" );
		writer.println( "<br>" );
		writer.println( "<br>" );
		writer.println( "All languages pre-configured in Telosys are documented here : <br>");
	}

	@Override
	protected String getItemLink(String page, String text) {
		return "<a href=\"languages/" + page + ".html\" >" + text + "</a>" ;
	}

	@Override
	protected void printTextAfterList(PrintWriter writer) {
		// nothing to print
	}

}