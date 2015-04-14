package de.muenchen.selenipo;

import java.util.List;

public interface PoGeneric {

	String getIdentifier();

	void setIdentifier(String identifier);

	List<Element> getElements();

	List<Transition> getTransitions();

}
