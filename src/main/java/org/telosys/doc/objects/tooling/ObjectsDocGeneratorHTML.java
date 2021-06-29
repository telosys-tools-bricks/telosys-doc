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

import org.telosys.tools.generator.GeneratorVersion;
import org.telosys.tools.generator.context.doc.tooling.ClassInfo;
import org.telosys.tools.generator.context.doc.tooling.MethodInfo;
import org.telosys.tools.generator.context.doc.tooling.MethodParameter;

public class ObjectsDocGeneratorHTML {
	
	private static final String CSS =  
			" h1 {"+
			"   font-size:28px;"+
			"   font-family: verdana;"+
			" } "+
			" p.otherNames { "+
			"   font-size:20px; "+
			"   font-family: verdana;"+
			" } "+
			" p.desc { "+
			"   font-size:12px; "+
			"   font-family: verdana; "+
			" } "+
			" p.doc { "+
			"   margin-left:2cm; "+
			" } "+
			" td.doc { "+
			"   font-size:12px; "+
			"   font-family: verdana; "+
			"   vertical-align:text-top; "+
			"   padding-top: 6px; "+
			"   padding-bottom: 12px; "+
			" }"+
			" tr.title {"+
			"   font-family: verdana;"+
			"   font-size:20px;"+
			"   font-weight:bold;"+
			"   background-color: #CCCCFF ;"+
			" }"+
			" code.example {"+
			"   font-size:14px;"+
			" }"+
			" code.simpledesc {"+
			"   font-size:15px;"+
			"   color: #000099;"+
			" }";
			

	public void generateDocFile(ClassInfo classInfo, String filePath) {
		File file = new File(filePath);
		if ( file.exists() ) {
			file.delete();
		}
		
		PrintWriter writer ;
		try {
			writer = new PrintWriter(filePath, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		generateDocFile( writer, classInfo);
		
		writer.close();
	}
	
	private void generateDocFile(PrintWriter writer, ClassInfo classInfo) {
		printBeginning(writer, classInfo );
		for ( MethodInfo methodInfo : classInfo.getMethodsInfo() ) {
			printMethodDoc(writer, methodInfo);
		}
		printEnd(writer);
	}
	
	private void printHead( PrintWriter writer, ClassInfo classInfo) {
		writer.println( "<head>" );
		writer.println( " <meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"> " );
		writer.println( " <title> $" + classInfo.getContextName() + "</title>");
		writer.println( " <style type=\"text/css\">");
		writer.println( CSS );
		writer.println( " </style>");
		writer.println( "</head>");
	}
	private void printBeginning( PrintWriter writer, ClassInfo classInfo) {
		writer.println(	"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\"> " );
		writer.println( "<html>" );
		printHead(writer, classInfo);
		writer.println( "<body>");
		writer.println( "<h1> $" + classInfo.getContextName() + "</h1>");
		
		//-------- PARAGRAPH "Other names"
		String[] otherNames = classInfo.getOtherContextName();
		if ( otherNames != null && otherNames.length > 0 ) {
			int i = 0 ;
			writer.print( "<p class=\"otherNames\">");
			writer.print( "Other name(s) : ");
			for ( String otherName : classInfo.getOtherContextName() ) {
				if ( i > 0 ) {
					writer.print( ",&nbsp;" );
				}
				writer.print( "<b>$" + otherName + "</b>" );
				i++;
			}
			writer.println( "</p>");
		}

		writer.println( "<p class=\"desc\"> ( doc for Telosys generator version " 
				+ GeneratorVersion.GENERATOR_VERSION + " ) </p>");
		
		writer.println( "<hr>");
		
		//-------- PARAGRAPH "doc" + "deprecated" + "example"
		writer.println( "<p class=\"desc\">");
		
		for ( String s : classInfo.getDocText()  ) {
			writer.println( s + "<br>" );
		}
		if ( classInfo.isDeprecated()  ) {
			writer.println( "<br>" );
			writer.println( "DEPRECATED (!) <br>" );
		}
		String[] exampleText = classInfo.getExampleText();
		if ( exampleText != null && exampleText.length > 0 ) {
			writer.println( "<br>" );
			writer.println( "<b>Example : </b><br>" );
			writer.println( "<code class=\"example\">" );
			for ( String s : exampleText ) {
				writer.println( "&nbsp;&nbsp;&nbsp;" + s + "<br>" );
			}
			writer.println( "</code>" );
		}

		if ( classInfo.getSince() != null && classInfo.getSince().trim().length() > 0 ) {
			writer.println( "<br>" );
			writer.println( "Since : " + classInfo.getSince() + "<br>" );
		}

		writer.println( "</p>");
		
		//-------- TABLE "Attributes and Methods"

		writer.println( "<table width=\"100%\" border=\"1\" cellspacing=\"0\">");		
		writer.println( "<TR class=\"title\">");
		writer.println( "  <TD>Attributes and methods</TD>");
		writer.println( "</TR>");

	}
	
	private void printEnd(PrintWriter writer) {
		writer.println( "</table>" );
		writer.println( "</body>" );
		writer.println( "</html>" );
	}

	private void printMethodDoc(PrintWriter writer, MethodInfo methodInfo) {

		writer.println( "<TR>" );
		writer.println( "<TD class=\"doc\" ><CODE class=\"simpledesc\"> <B>." + methodInfo.getSimpleDescription() + "</B> </CODE>" );
		writer.println( "<p class=\"doc\">" );
		if ( methodInfo.isDeprecated() ) {
			writer.println( "<b>Deprecated.</b><br>" );
			writer.println( "<br>" );
		}
		for ( String s : methodInfo.getDocText()  ) {
			writer.println( s + "<br>" );
		}
		if ( methodInfo.hasParameters() ) {
			writer.println( "<br>" );
			writer.println( "<b>Parameters : </b><br>" );
			for ( MethodParameter p : methodInfo.getParameters() ) {
				writer.println("&nbsp;&nbsp;&nbsp;<b>" + p.getName() + "</b> : " + p.getDescription() + "<br>");
			}			
		}
		if ( methodInfo.hasExampleText() ) {
			writer.println( "<br>" );
			writer.println( "<b>Example : </b><br>" );
			writer.println( "<code>" );
			for ( String s : methodInfo.getExampleText() ) {
				writer.println( "&nbsp;&nbsp;&nbsp;" + s + "<br>" );
			}
			writer.println( "</code>" );
		}
		if ( methodInfo.hasSince() ) {
			writer.println( "<br>" );
			writer.println( "<b>Since : </b>" + methodInfo.getSince() + "<br>" );
		}
		writer.println( "</p>" );
		writer.println( "</TD>" );
		writer.println( "</TR>" );
	}
}
