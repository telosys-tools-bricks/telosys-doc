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
package org.telosys.doc.index;

import org.telosys.doc.DocVersion;
import org.telosys.doc.commons.DestinationFolder;
import org.telosys.doc.commons.Html;
import org.telosys.doc.commons.Logger;
import org.telosys.tools.commons.FileUtil;

public class IndexGeneratorHTML {

	private IndexGeneratorHTML() {
	}

	public static void genDoc() {
		Logger.log("Index doc generation... ");
		String destDir = DestinationFolder.getFullDestinationDir(DocVersion.DOC_VERSION_DIR);
		String fullFileName = FileUtil.buildFilePath(destDir, "index.html");
		Logger.log(" . " + fullFileName);
		generateFile(fullFileName);
	}
	
	public static void generateFile(String fullFileName) {
		
		String title = "Telosys " + DocVersion.DOC_VERSION + " documentation" ;
		
		Html htmlPage = new Html(fullFileName) ;
		htmlPage.startOfPage(title);
		
		// Generate content
		printPageContent(htmlPage, title);
		
		htmlPage.endOfPage();
	}
	
	private static void printPageContent( Html htmlPage, String title ) {
		htmlPage.println( "<h1>" + title + "</h1>");
		
		htmlPage.println(
		  "<p>"
		+ "The reference documentation provides details on the technical aspects of Telosys.<br>"
		+ "It contains all the information required to create your own templates or adapt existing templates."
		+ "</p>"
		);

		htmlPage.println("<h3>");
		htmlPage.printAnchor("objects/index.html", "Telosys objects usable in templates");
		htmlPage.println("</h3>");

		htmlPage.println("<h3>");
		htmlPage.printAnchor("languages/index.html", "Telosys predefined target languages");
		htmlPage.println("</h3>");
		
//		<h3> <a href="objects/index.html">Telosys objects usable in templates</a></h3>
//		<h3> <a href="languages/index.html">Telosys predefined target languages </a></h3>		
		htmlPage.println( "<br><br>" );
	}
}
