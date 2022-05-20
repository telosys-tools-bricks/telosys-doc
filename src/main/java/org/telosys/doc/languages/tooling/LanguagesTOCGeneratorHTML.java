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
import java.util.List;

import org.telosys.doc.DocVersion;
import org.telosys.doc.commons.AbstractTOCGeneratorHTML;
import org.telosys.doc.commons.ItemLink;

public class LanguagesTOCGeneratorHTML extends AbstractTOCGeneratorHTML {

	private static final String TITLE = "Telosys " + DocVersion.DOC_VERSION + " - target languages";
	
	public LanguagesTOCGeneratorHTML(String fullFileName, List<ItemLink> itemLinks) {
		super(fullFileName, TITLE, itemLinks);
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
		String href = "" ;
		if ( isIndexFile() ) {
			// in same directory
			href = page + ".html" ;
		}
		else {
			// in subdirectory
			href = "languages/" + page + ".html" ;
		}
		return "<a href=\"" + href + "\" >" + text + "</a>";
	}

	@Override
	protected void printTextAfterList(PrintWriter writer) {
		// nothing to print
	}

}
