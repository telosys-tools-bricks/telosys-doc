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

import org.telosys.doc.commons.AbstractTOCGeneratorHTML;
import org.telosys.doc.commons.ItemLink;

public class ObjectsTOCGeneratorHTML extends AbstractTOCGeneratorHTML {

	private static final String TITLE = "Telosys objects reference";
		
	public ObjectsTOCGeneratorHTML(String fullFileName, ItemLink[] sortedLinks ) {
		super(fullFileName, TITLE, sortedLinks);
	}

	@Override
	protected void printTextBeforeList(PrintWriter writer) {
//		writer.println( "Templates use predefined objects stored in the Velocity Context by the generator.<br>" );
//		writer.println( "<br>" );
//		writer.println( "Some objects are always available ($entity, $today, etc),<br>");
//		writer.println( "others are retrieved from existing objects ($attribute, $link).<br>");
//		writer.println( "<br>" );
//		writer.println( "All these objects are documented here : <br>");
		
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
		return "<a href=\"objects/" + page + ".html\" >$" + text + "</a>";
	}

	@Override
	protected void printTextAfterList(PrintWriter writer) {
		// nothing to print
	}
}
