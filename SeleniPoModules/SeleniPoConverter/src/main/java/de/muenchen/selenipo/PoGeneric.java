package de.muenchen.selenipo;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for the informations that specify a pageObject
 * 
 * @author matthias
 *
 */
public class PoGeneric {

	private String identifier;
	private List<Element> elements = new ArrayList<Element>();
	private List<Transition> transitions = new ArrayList<Transition>();

	public PoGeneric(String identifier) {
		super();
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

}
