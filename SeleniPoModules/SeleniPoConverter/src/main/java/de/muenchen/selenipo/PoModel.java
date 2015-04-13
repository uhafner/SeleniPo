package de.muenchen.selenipo;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper that contains a list of pageobjects
 * 
 * @author matthias
 *
 */
public class PoModel {

	private List<PoGeneric> poGenerics = new ArrayList<PoGeneric>();

	public PoModel() {
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
