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

public class Html {
	
	private final String fullFileName ;
	
	private PrintWriter writer;

	/**
	 * Constructor
	 * @param fullFileName
	 */
	public Html(String fullFileName) {
		super();
		this.fullFileName = fullFileName;
	}

	/**
	 * Prints the start of html page including "body" opening tag
	 * @param title
	 * @return
	 */
	public PrintWriter startOfPage(String title) {
		File file = new File(fullFileName);
		if ( file.exists() ) {
			file.delete();
		}
		
		try {
			writer = new PrintWriter(fullFileName, "UTF-8");
		} catch (FileNotFoundException|UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		printStartOfPage(title);	
		return writer;
	}
	
	/**
	 * Prints the end of html page including "body" closing tag
	 */
	public void endOfPage() {
		printEndOfPage();
		writer.close();
	}
	
	private void printStartOfPage(String title) {
		writer.println(	"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">" );
		writer.println( "<html>" );
		
		writer.println( "<head>" );
		writer.println( "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
		writer.println( "  <title>" + title + "</title>");
		writer.println( "  <style type=\"text/css\">");
		writer.println( "    p.desc {");
		writer.println( "      font-size:12px;");
		writer.println( "      font-family: verdana;");
		writer.println( "    }");
		writer.println( "    code.example { ");
		writer.println( "      font-size:14px;");
		writer.println( "    }");
		writer.println( "  </style>");
		writer.println( "</head>");

		writer.println( "<body>");
		
	}

	public void printEndOfPage() {
		writer.println( "</body>" );
		writer.println( "</html>" );
	}
	
	public void println(String s) {
		writer.println(s);
	}
	
	public void printAnchor(String href, String text) {
		writer.println( "<a href=\"" + href + "\">");
		writer.println( text);
		writer.println( "</a>");
	}
}
