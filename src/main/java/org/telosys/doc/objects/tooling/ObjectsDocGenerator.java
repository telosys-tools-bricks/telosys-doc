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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.telosys.doc.commons.DestinationFolder;
import org.telosys.doc.commons.ItemLink;
import org.telosys.doc.commons.Logger;
import org.telosys.tools.commons.FileUtil;
import org.telosys.tools.generator.context.doc.tooling.ClassInfo;
import org.telosys.tools.generator.context.doc.tooling.DocBuilder;

public class ObjectsDocGenerator {

	private static final String TOC_FILENAME   = "doc-objects.html" ;
	private static final String INDEX_FILENAME = "index.html" ;
	private static final boolean DETAILED_LOG = false ;
	
	/**
	 * Private constructor
	 */
	private ObjectsDocGenerator() {
	}
	
	/**
	 * Generates Telosys objects reference documentation in the given directory
	 * @param destDir
	 * @return
	 */
	public static int generateHtmlDoc(String destDir) {

		log( "Objects documentation generation" );
		log( "Objects doc - STEP 1 : preparation" );
		File fileDir = DestinationFolder.prepare(destDir);
		
		DocBuilder docBuilder = new DocBuilder();
		Map<String,ClassInfo> classesInfo = docBuilder.getVelocityClassesInfo() ;
		if ( DETAILED_LOG ) printClassesInfo(classesInfo);
		
		Set<String> uniqueNames = buildUniqueNames(classesInfo);
		if ( DETAILED_LOG ) printUniqueNames(uniqueNames);
		
		List<String> sortedNames = buildSortedNames(uniqueNames);
		if ( DETAILED_LOG ) printSortedNames(sortedNames);
		
		// Generate object html doc file ( one for each object )
		log( "Objects doc - STEP 2 : objects pages generation" );
		int c = generatePages(destDir, sortedNames, classesInfo);
		
		// Generate the Table Of Contents file in the parent directory
		log( "Objects doc - STEP 3 : table of content generation" );
		String tocFullFileName = FileUtil.buildFilePath(fileDir.getParent(), TOC_FILENAME);
		generateTOC(tocFullFileName, sortedNames);
		
		// Generate the index file in the same directory
		log( "Objects doc - STEP 4 : index generation" );
		String indexFullFileName = FileUtil.buildFilePath(fileDir.getPath(), INDEX_FILENAME);
		generateTOC(indexFullFileName, sortedNames);

		return c;
	}
	
	private static int generatePages(String destDir, List<String> names, Map<String,ClassInfo> classesInfo) { 
		
		// Generate object html doc file ( one for each object )
		ObjectsDocGeneratorHTML htmlGenerator = new ObjectsDocGeneratorHTML();

		log("Names (size=" + names.size() + ") : " );
		int c = 0 ;
		for ( String name : names ) {
			ClassInfo classInfo = classesInfo.get(name);
			String shortFileName = classInfo.getContextName() + ".html" ;
			String fullFileName = FileUtil.buildFilePath(destDir, shortFileName);
			log(" . " + name + " (" + classInfo.getContextName() + ") --> " + fullFileName );
			htmlGenerator.generateDocFile(classInfo, fullFileName);
			c++;
		}
		return c;
	}
	
	/**
	 * Generates Table Of Content file
	 * @param tocFullFileName
	 * @param sortedNames
	 */
	private static void generateTOC(String tocFullFileName, List<String> sortedNames) {
		List<ItemLink> itemLinks = new LinkedList<>();
		for ( String s : sortedNames ) {
			itemLinks.add(new ItemLink(s, s) ); // same string for "object name" and "page name"
		}
		log(" . " + tocFullFileName);
		ObjectsTOCGeneratorHTML tocGenerator = new ObjectsTOCGeneratorHTML(tocFullFileName, itemLinks);
		tocGenerator.generateTOCFile();
	}
	
	private static Set<String> buildUniqueNames(Map<String,ClassInfo> map) {
		Set<String> uniqueNames = new HashSet<>();
		for ( ClassInfo classInfo : map.values() ) {
			uniqueNames.add(classInfo.getContextName());
		}
		return uniqueNames;
	}

	private static List<String> buildSortedNames(Set<String> names) {
		List<String> list = new ArrayList<>(names);
		java.util.Collections.sort(list);
		return list;
	}
	
	private static void log(String msg) {
		Logger.log(msg);
	}

	private static void printClassesInfo(Map<String,ClassInfo> classesInfo) {
		Set<String> names = classesInfo.keySet();
		log("ClassInfo names ( Map / size=" + names.size() + ") : " );
		for ( String name : names ) {
			ClassInfo info = classesInfo.get(name);
			log(" . '" + name + "' : $" + info.getContextName() + " (" + info.getJavaClassName() + ")");
		}
	}

	private static void printUniqueNames(Set<String> uniqueNames) {
		log("Uniques names (size=" + uniqueNames.size() + ") : " );
		for ( String name : uniqueNames ) {
			log(" . '" + name + "' " );
		}
	}
	
	private static void printSortedNames(List<String> list) {
		log("Sorted names (size=" + list.size() + ") : " );
		for ( String name : list ) {
			log(" . '" + name + "' " );
		}
	}
	
}
