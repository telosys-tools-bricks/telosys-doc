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
package org.telosys.doc.objects.tooling;

import java.io.PrintWriter;
import java.util.List;

import org.telosys.doc.DocVersion;
import org.telosys.doc.commons.AbstractTOCGeneratorHTML;
import org.telosys.doc.commons.ItemLink;

public class ObjectsTOCGeneratorHTML extends AbstractTOCGeneratorHTML {

	private static final String TITLE = "Telosys " + DocVersion.DOC_VERSION + " - objects reference";
		
	public ObjectsTOCGeneratorHTML(String fullFileName, List<ItemLink> sortedLinks ) {
		super(fullFileName, TITLE, sortedLinks);
		String desc = "Documentation for Telosys version " + DocVersion.DOC_VERSION ;
		setDescription(desc);
	}

	@Override
	protected void printTextBeforeList(PrintWriter writer) {
		writer.println( "Telosys provides a set of predefined objects usable in the templates :<br>" );
		writer.println( "<ul>" );
		writer.println( "<li> Objects giving access to the current model ( $model, $entity, $attribute, $link, etc )</li>" );
		writer.println( "<li> Utility functions and tools ( $fn, $loader )</li>" );
		writer.println( "<li> Generation environment ( $env, $project, $target, etc )</li>" );
		writer.println( "<li> Functions dedicated to certain types of generation ($java, $h2, $jpa, etc)</li>" );
		writer.println( "</ul>" );
		writer.println( "The reference documentation for each object is available in the following pages :" );
	}

	@Override
	protected String getItemLink(String page, String text) {
		String href = "" ;
		if ( isIndexFile() ) {
			// in same directory
			href = page + ".html" ;
		}
		else {
			// in "objects" directory
			href = "objects/" + page + ".html" ;
		}
		return "<a href=\"" + href + "\" >$" + text + "</a>";
	}

	@Override
	protected void printTextAfterList(PrintWriter writer) {
		// nothing to print
	}
}
