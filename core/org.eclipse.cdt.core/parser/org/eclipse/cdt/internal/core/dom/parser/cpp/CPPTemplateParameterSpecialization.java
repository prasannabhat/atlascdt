/*******************************************************************************
 * Copyright (c) 2013 Nathan Ridge.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nathan Ridge
 *******************************************************************************/
package org.eclipse.cdt.internal.core.dom.parser.cpp;

import org.eclipse.cdt.core.dom.ILinkage;
import org.eclipse.cdt.core.dom.ast.DOMException;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPScope;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPSpecialization;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPTemplateArgument;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPTemplateParameter;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPTemplateParameterMap;
import org.eclipse.cdt.core.parser.util.ObjectMap;
import org.eclipse.cdt.internal.core.dom.Linkage;
import org.eclipse.cdt.internal.core.dom.parser.cpp.semantics.CPPTemplates;
import org.eclipse.core.runtime.PlatformObject;

/**
 * A specialization of a template parameter.
 *
 * This class provides common implementation for CPPTemplateNonTypeParameterSpecialization,
 * CPPTemplateTypeParameterSpecialization, and CPPTemplateTemplateParameterSpecialization.
 */
public abstract class CPPTemplateParameterSpecialization extends PlatformObject
		implements ICPPTemplateParameter, ICPPSpecialization {
	private final ICPPSpecialization fOwner;
	private final ICPPScope fScope;
	private final ICPPTemplateParameter fSpecialized;
	private final ICPPTemplateParameterMap fTemplateParameterMap;
	private final ICPPTemplateArgument fDefaultValue;

	public CPPTemplateParameterSpecialization(ICPPSpecialization owner, ICPPScope scope, ICPPTemplateParameter specialized,
			ICPPTemplateArgument defaultValue) {
		fOwner = owner;
		fScope = scope;
		fSpecialized = specialized;
		fTemplateParameterMap = owner.getTemplateParameterMap();
		fDefaultValue = defaultValue;
	}

	@Override
	public String[] getQualifiedName() throws DOMException {
		return fSpecialized.getQualifiedName();
	}

	@Override
	public char[][] getQualifiedNameCharArray() throws DOMException {
		return fSpecialized.getQualifiedNameCharArray();
	}

	@Override
	public boolean isGloballyQualified() throws DOMException {
		return false;
	}

	@Override
	public String getName() {
		return fSpecialized.getName();
	}

	@Override
	public char[] getNameCharArray() {
		return fSpecialized.getNameCharArray();
	}

	@Override
	public ILinkage getLinkage() {
		return Linkage.CPP_LINKAGE;
	}

	@Override
	public ICPPSpecialization getOwner() {
		return fOwner;
	}

	@Override
	public ICPPScope getScope() throws DOMException {
		return fScope;
	}

	@Override
	public ICPPTemplateParameter getSpecializedBinding() {
		return fSpecialized;
	}

	@Override
	public ICPPTemplateParameterMap getTemplateParameterMap() {
		return fTemplateParameterMap;
	}

	@Override
	@Deprecated
	public ObjectMap getArgumentMap() {
		return CPPTemplates.getArgumentMap(this, getTemplateParameterMap());
	}

	@Override
	public short getParameterPosition() {
		return fSpecialized.getParameterPosition();
	}

	@Override
	public short getTemplateNestingLevel() {
		return fSpecialized.getTemplateNestingLevel();
	}

	@Override
	public int getParameterID() {
		return fSpecialized.getParameterID();
	}

	@Override
	public ICPPTemplateArgument getDefaultValue() {
		return fDefaultValue;
	}

	@Override
	public boolean isParameterPack() {
		return fSpecialized.isParameterPack();
	}
}
