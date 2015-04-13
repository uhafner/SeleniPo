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

}
