package de.muenchen.selenipo;

public interface Element {

	Selector getType();

	void setType(Selector type);

	public String getLocator();

	public void setLocator(String locator);

	public String getIdentefier();

	public void setIdentefier(String identefier);

}
