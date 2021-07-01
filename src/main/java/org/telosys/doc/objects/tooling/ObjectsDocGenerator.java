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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.telosys.doc.commons.DestinationFolder;
import org.telosys.doc.commons.ItemLink;
import org.telosys.tools.commons.FileUtil;
import org.telosys.tools.generator.context.doc.tooling.ClassInfo;
import org.telosys.tools.generator.context.doc.tooling.DocBuilder;

public class ObjectsDocGenerator {

	private static final String TOC_FILENAME   = "doc-objects.html" ;
	private static final String INDEX_FILENAME = "index.html" ;
	
	private ObjectsDocGenerator() {
	}
	
	/**
	 * Generates Telosys objects reference documentation in the given directory
	 * @param destDir
	 * @return
	 */
	public static int generateHtmlDoc(String destDir) {

		log( "HTML documentation generation" );
		File fileDir = DestinationFolder.prepare(destDir);
		DocBuilder docBuilder = new DocBuilder();
		
		Map<String,ClassInfo> classesInfo = docBuilder.getVelocityClassesInfo() ;

		Set<String> names = classesInfo.keySet();
		log("ClassInfo names (size=" + names.size() + ") : " );
		for ( String name : names ) {
			log(" . " + name );
		}
		
		List<String> sortedNames = sortList(names);
		Set<String> uniqueNames = new HashSet<>();
		
		// Generate object html doc file ( one for each object )
		ObjectsDocGeneratorHTML htmlGenerator = new ObjectsDocGeneratorHTML();

		log("Sorted context names (size=" + sortedNames.size() + ") : " );
		int c = 0 ;
		for ( String name : sortedNames ) {
			ClassInfo classInfo = classesInfo.get(name);
			String shortFileName = classInfo.getContextName() + ".html" ;
			String fullFileName = FileUtil.buildFilePath(destDir, shortFileName);
			log(" . " + name + " (" + classInfo.getContextName() + ") --> " + fullFileName );
			htmlGenerator.generateDocFile(classInfo, fullFileName);
			uniqueNames.add(classInfo.getContextName());
			c++;
		}
		
		// Generate the Table Of Contents file in the parent directory
//		List<String> sortedUniqueNames = new ArrayList<>(uniqueNames);
//		Collections.sort(sortedUniqueNames);
//		ItemLink[] itemLinks = new ItemLink[sortedUniqueNames.size()];
//		int i = 0 ;
//		for ( String s : sortedUniqueNames ) {
//			itemLinks[i++] = new ItemLink(s, s);
//		}
//		String tocFullFileName = FileUtil.buildFilePath(fileDir.getParent(), TOC_FILENAME);
//		ObjectsTOCGeneratorHTML tocGenerator = new ObjectsTOCGeneratorHTML(tocFullFileName, itemLinks);
//		tocGenerator.generateTOCFile();
		
		String tocFullFileName = FileUtil.buildFilePath(fileDir.getParent(), TOC_FILENAME);
		generateTOC(tocFullFileName, uniqueNames);
		
		// Generate the index file in the same directory
		String indexFullFileName = FileUtil.buildFilePath(fileDir.getPath(), INDEX_FILENAME);
		generateTOC(indexFullFileName, uniqueNames);

		return c;
	}
	
	/**
	 * Generates Table Of Content file
	 * @param tocFullFileName
	 * @param uniqueNames
	 */
	private static void generateTOC(String tocFullFileName, Set<String> uniqueNames) {
		List<String> sortedUniqueNames = new ArrayList<>(uniqueNames);
		Collections.sort(sortedUniqueNames);
		ItemLink[] itemLinks = new ItemLink[sortedUniqueNames.size()];
		int i = 0 ;
		for ( String s : sortedUniqueNames ) {
			itemLinks[i++] = new ItemLink(s, s);
		}
		ObjectsTOCGeneratorHTML tocGenerator = new ObjectsTOCGeneratorHTML(tocFullFileName, itemLinks);
		tocGenerator.generateTOCFile();
	}
	
	private static <T extends Comparable<? super T>> List<T> sortList(Collection<T> c) {
		  List<T> list = new ArrayList<>(c);
		  java.util.Collections.sort(list);
		  return list;
	}
	
	private static void log(String msg) {
		System.out.println(msg);
	}
}
