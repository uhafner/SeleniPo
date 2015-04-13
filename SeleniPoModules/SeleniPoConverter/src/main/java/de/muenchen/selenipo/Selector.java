package de.muenchen.selenipo;

public enum Selector {

	LINK("link"), XPATH("xpath"), HREF("href"), CHECKBOX("checkbox"), RADIOBUTTON(
			"radioButton"), INPUT("input"), BUTTON("button"), TAGNAME("tagName"), OPTION(
			"option"), ID("id");

	private String byMethodName;

	private Selector(String byMethodName) {
		this.byMethodName = byMethodName;
	}

	public String getByMethodName() {
		return byMethodName;
	}

	public void setByMethodName(String byMethodName) {
		this.byMethodName = byMethodName;
	}
	
	
}
