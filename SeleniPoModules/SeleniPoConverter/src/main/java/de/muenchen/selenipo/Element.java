package de.muenchen.selenipo;

public class Element {

	private Selector type;
	private String locator;

	public Element(Selector type, String locator) {
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

	@Override
	public String toString() {
		return "------ Element [type=" + type + ", locator=" + locator + "]"
				+ System.lineSeparator();
	}

}
