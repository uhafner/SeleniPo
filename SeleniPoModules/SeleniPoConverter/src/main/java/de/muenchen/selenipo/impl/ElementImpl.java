package de.muenchen.selenipo.impl;

import de.muenchen.selenipo.Element;
import de.muenchen.selenipo.Selector;

public class ElementImpl implements Element {

	private Selector type;
	private String locator;
	private String identefier;

	public ElementImpl(Selector type, String locator) {
		super();
		this.type = type;
		this.locator = locator;
	}

	public Selector getType() {
		return type;
	}

	public void setType(Selector type) {
		this.type = type;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}
	

	public String getIdentifier() {
		return identefier;
	}

	public void setIdentifier(String identefier) {
		this.identefier = identefier;
	}

	@Override
	public String toString() {
		return "------ Element [type=" + type + ", locator=" + locator + "]"
				+ System.lineSeparator();
	}


}
