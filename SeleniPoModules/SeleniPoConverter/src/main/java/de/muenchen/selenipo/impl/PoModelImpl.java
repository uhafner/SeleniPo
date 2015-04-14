package de.muenchen.selenipo.impl;

import java.util.ArrayList;
import java.util.List;

import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.PoModel;

/**
 * Wrapper that contains a list of pageobjects
 * 
 * @author matthias
 *
 */
public class PoModelImpl implements PoModel {

	private List<PoGeneric> poGenerics = new ArrayList<PoGeneric>();

	public PoModelImpl() {
		super();
	}

	public List<PoGeneric> getPoGenerics() {
		return poGenerics;
	}

	public void setPoGenerics(List<PoGeneric> poGenerics) {
		this.poGenerics = poGenerics;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PoModel: ");
		for (PoGeneric poGeneric : poGenerics) {
			sb.append(poGeneric.toString() + System.lineSeparator());
		}
		return sb.toString();
	}

}
