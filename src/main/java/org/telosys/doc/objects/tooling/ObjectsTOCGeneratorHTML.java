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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.telosys.tools.generator.GeneratorVersion;

public class ObjectsTOCGeneratorHTML {

	private static final String TITLE = "Telosys templates objects";

	public void generateTOCFile(String tocFullFileName, List<String> sortedNames) {
		File file = new File(tocFullFileName);
		if ( file.exists() ) {
			file.delete();
		}
		
		PrintWriter writer ;
		try {
			writer = new PrintWriter(tocFullFileName, "UTF-8");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		// Generate content
		printBeginning(writer);
		printList(writer, sortedNames);
		printEnd(writer);
		
		writer.close();
	}
	
	private void printBeginning( PrintWriter writer) {
		writer.println(	"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">		" );
		writer.println( "<html>								" );
		
		writer.println( "<head>								" );
		writer.println( "	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">	" );
		writer.println( "	<title>" + TITLE + "</title>	");
		writer.println( "	<style type=\"text/css\">		");
/***
		writer.println( "	h1 {							");
		writer.println( "		font-size:28px;				");
		writer.println( "		font-family: verdana		");
		writer.println( "	}								");
		writer.println( "	p.otherNames{					");
		writer.println( "		font-size:20px;				");
		writer.println( "		font-family: verdana;		");
		writer.println( "	}								");
**/
		writer.println( "	p.desc {						");
		writer.println( "		font-size:12px;				");
		writer.println( "		font-family: verdana;		");
		writer.println( "	}								");
/**
		writer.println( "	p.doc {							");
		writer.println( "		margin-left:2cm;			");
		writer.println( "	}								");
		writer.println( "	td.doc {						");
		writer.println( "		font-size:12px;				");
		writer.println( "		font-family: verdana;		");
		writer.println( "		vertical-align:text-top;	");
		writer.println( "		padding-top: 6px;			");
		writer.println( "		padding-bottom: 12px;		");
		writer.println( "	}								");
		writer.println( "	tr.title {						");
		writer.println( "		font-family: verdana;		");
		writer.println( "		font-size:20px;				");
		writer.println( "		font-weight:bold;			");
		writer.println( "		background-color: #CCCCFF ;	");
		writer.println( "	}								");
		writer.println( "	code.simpledesc {				");
		writer.println( "		font-size:15px;				");
		writer.println( "		color: #000099; 			");
		writer.println( "	}								");
***/
		writer.println( "	</style>						");
		writer.println( "</head>							");

		writer.println( "<body>	");
		writer.println( "<h1>" + TITLE + "</h1>	");
		writer.println( "<p class=\"desc\">	Generator version : <b>" + GeneratorVersion.GENERATOR_VERSION + "</b> </p>");
		writer.println( "<p>" );
		writer.println( "Templates use predefined objects stored in the Velocity Context by the generator.<br>" );
		writer.println( "<br>" );
		writer.println( "Some objects are always available ($entity, $today, etc),<br>");
		writer.println( "others are retrieved from existing objects ($attribute, $link).<br>");
		writer.println( "<br>" );
		writer.println( "All these objects are documented here : <br>");
		writer.println( "<ul>" );
	}
	
	private void printList(PrintWriter writer, List<String> sortedNames) {
		for ( String objectName : sortedNames ) {
			writer.println( "<li> <a href=\"objects/" + objectName + ".html\"      >$" + objectName + "</a> </li>" );
		}
	}
	
	private void printEnd(PrintWriter writer) {
		writer.println( "</ul>" );
		writer.println( "</p>" );
		writer.println( "" );
		writer.println( "</body>" );
		writer.println( "</html>" );
	}

}
