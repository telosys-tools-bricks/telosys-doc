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
package org.telosys.doc.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.telosys.tools.generator.GeneratorVersion;

public abstract class AbstractTOCGeneratorHTML {

	protected abstract void printTextBeforeList(PrintWriter writer) ;

	protected abstract String getItemLink(String page, String text) ;
	
	protected abstract void printTextAfterList(PrintWriter writer) ;
	

	private final String fullFileName ;
	private final String title ;
	private final ItemLink[] sortedLinks ;

	public AbstractTOCGeneratorHTML(String fullFileName, String title, ItemLink[] sortedLinks) {
		super();
		this.fullFileName = fullFileName;
		this.title = title;
		this.sortedLinks = sortedLinks;
	}

	public void generateTOCFile() {
		File file = new File(fullFileName);
		if ( file.exists() ) {
			file.delete();
		}
		
		PrintWriter writer ;
		try {
			writer = new PrintWriter(fullFileName, "UTF-8");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		// Generate content
		printPage(writer);
		
		writer.close();
	}
	
	private void printPage( PrintWriter writer) {
		writer.println(	"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">		" );
		writer.println( "<html>" );
		
		writer.println( "<head>" );
		writer.println( "	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">	" );
		writer.println( "	<title>" + title + "</title>	");
		writer.println( "	<style type=\"text/css\">		");
		writer.println( "	p.desc {						");
		writer.println( "		font-size:12px;				");
		writer.println( "		font-family: verdana;		");
		writer.println( "	}								");
		writer.println( "	code.example {				");
		writer.println( "		font-size:14px;				");
		writer.println( "	}								");
		writer.println( "	</style>						");
		writer.println( "</head>							");

		writer.println( "<body>	");
		writer.println( "<h1>" + title + "</h1>	");
		writer.println( "<p class=\"desc\">	Generator version : <b>" + GeneratorVersion.GENERATOR_VERSION + "</b> </p>");
		writer.println( "<p>" );

		printTextBeforeList(writer);
		
		//--- Print TOC items list
		writer.println( "<ul>" );
		for ( ItemLink link : sortedLinks ) {
			writer.println( "<li> " + getItemLink(link.getPage(), link.getText() ) + "</a> </li>" );
		}
		writer.println( "</ul>" );
		
		printTextAfterList(writer);
		
		writer.println( "</p>" );
		writer.println( "</body>" );
		writer.println( "</html>" );
	}

}
