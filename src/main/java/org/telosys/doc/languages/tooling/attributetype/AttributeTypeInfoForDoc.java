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
package org.telosys.doc.languages.tooling.attributetype;

import org.telosys.tools.generator.languages.types.AttributeTypeInfo;

public class AttributeTypeInfoForDoc implements AttributeTypeInfo {

	private final String  neutralType ;

	private final boolean notNull ;

	private final boolean primitiveTypeExpected ;
	private final boolean objectTypeExpected ;
	
	private final boolean unsignedTypeExpected ;

	/**
	 * Constructor
	 * @param neutralType
	 * @param attributeTypeAnnotation
	 */
	public AttributeTypeInfoForDoc(String neutralType, AttributeTypeAnnotation attributeTypeAnnotation ) {
		super();
		this.neutralType           = neutralType;
		this.notNull               = attributeTypeAnnotation == AttributeTypeAnnotation.NOT_NULL ;
		this.primitiveTypeExpected = attributeTypeAnnotation == AttributeTypeAnnotation.PRIMITIVE_TYPE;
		this.objectTypeExpected    = attributeTypeAnnotation == AttributeTypeAnnotation.OBJECT_TYPE;
		this.unsignedTypeExpected  = attributeTypeAnnotation == AttributeTypeAnnotation.UNSIGNED_TYPE ;
	}

	@Override
	public String getNeutralType() {
		return neutralType;
	}

	@Override
	public boolean isNotNull() {
		return notNull;
	}

	@Override
	public boolean isPrimitiveTypeExpected() {
		return primitiveTypeExpected;
	}

	@Override
	public boolean isObjectTypeExpected() {
		return objectTypeExpected;
	}

	@Override
	public boolean isUnsignedTypeExpected() {
		return unsignedTypeExpected;
	}

}
