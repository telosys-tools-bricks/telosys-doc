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

import java.util.List;
import java.util.Map;

import org.telosys.tools.generator.context.names.ContextNames;

public class ContextInfo {

	private final static MethodInfo[] VOID_METHOD_INFO_ARRAY = {} ;
	private final Map<String,ClassInfo> classesInfo ;
	private final EditorDoc editorDoc ;
	
	public ContextInfo() {
		super();
		DocBuilder docBuilder = new DocBuilder();
		this.classesInfo = docBuilder.getVelocityClassesInfo() ;
		this.editorDoc = new EditorDoc() ;
	}

	/**
	 * Returns the context variables names ( "SRC", "AMP", "LT", .. ) 
	 * @return
	 */
	public String[] getVariableNames() {
		return ContextNames.getVariableNames() ;
	}
	
	/**
	 * Returns the context objects names ( "fn", "today", ... )
	 * @return
	 */
	public String[] getObjectNames() {
		return ContextNames.getObjectNames();
	}
	
	/**
	 * Returns the names defined by convention ( "attribute", "link", ... )
	 * @return
	 */
	public String[] getPredefinedNames() {
		return ContextNames.getPredefinedNames();
	}
	
	/**
	 * Returns the context objects names ( "fn", "today", ... ) <br>
	 *  and variables names ( "SRC", "AMP", "LT", .. ) 
	 * @return
	 */
	public String[] getObjectAndVariableNames() {
		return ContextNames.getObjectAndVariableNames();
	}
	
	/**
	 * Returns general information about an object (class level information)
	 * @param objectName the object name ( e.g. "fn", "today", ... )
	 * @return the class information or null if no information found
	 */
	public ClassInfo getClassInfo(String objectName) {
		ClassInfo classInfo = classesInfo.get(objectName);
		return classInfo ;
	}

	/**
	 * Returns information about a method or attribute 
	 * @param objectName the object name ( e.g. "fn", "today", ... )
	 * @param methodSignature the signature as provided by "MethodInfo.getSignature()"
	 * @return
	 */
	public MethodInfo getMethodInfo(String objectName, String methodSignature) {
		ClassInfo classInfo = classesInfo.get(objectName);
		if ( classInfo != null ) {
			return classInfo.getMethodInfo(methodSignature);
		}
		return null ;
	}

	/**
	 * Returns information about all methods of an object  
	 * @param objectName the object name ( e.g. "fn", "today", ... )
	 * @return array of MethodInfo or null if none
	 */
	public MethodInfo[] getAllMethodsInfo(String objectName) {
		ClassInfo classInfo = classesInfo.get(objectName);
		if ( classInfo != null ) {
			List<MethodInfo> list = classInfo.getMethodsInfo() ;
			return list.toArray( VOID_METHOD_INFO_ARRAY );
		}
		return null ;
	}

	/**
	 * Returns an object documentation in a string containing the documentation formatted in HTML
	 * @param objectName the object name ( e.g. "fn", "today", ... )
	 * @return the documentation, or null if the given object name is unknown
	 */
	public String getClassDocumentation(String objectName) {
		ClassInfo classInfo = classesInfo.get(objectName);
		if ( classInfo != null ) {
			return editorDoc.getClassDoc(classInfo);
		}
		else {
			//return "Unknown object '" + objectName + "' !";
			return null ;
		}
	}
	
	/**
	 * Returns a method/attribute documentation in a string containing the documentation formatted in HTML
	 * @param objectName the object name ( e.g. "fn", "today", ... )
	 * @param methodSignature the signature as provided by "MethodInfo.getSignature()"
	 * @return the documentation, or null if the method is unknown
	 */
	public String getMethodDocumentation(String objectName, String methodSignature) {
		ClassInfo classInfo = classesInfo.get(objectName);
		if ( classInfo != null ) {
			MethodInfo methodInfo = classInfo.getMethodInfo(methodSignature);
			if ( methodInfo != null ) {
				return editorDoc.getMethodDoc(classInfo, methodInfo);
			}
			else {
				//return "Unknown method signature '" + methodSignature + "' for object '" + objectName + "' !";
				return null ;
			}
		}
		else {
			//return "Unknown object '" + objectName + "' !";
			return null ;
		}
	}
	
}
