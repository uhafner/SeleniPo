package de.muenchen.selenipo.impl;

import java.util.ArrayList;
import java.util.List;

import de.muenchen.selenipo.Element;
import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.Transition;

/**
 * Wrapper for the informations that specify a pageObject.
 * 
 * @author matthias
 *
 */
public class PoGenericImpl implements PoGeneric {

	private String identifier;
	private List<Element> elements = new ArrayList<Element>();
	private List<Transition> transitions = new ArrayList<Transition>();

	public PoGenericImpl(String identifier) {
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--PoGeneric - " + identifier + ": " + System.lineSeparator());
		sb.append("----Elements: " + System.lineSeparator());
		for (Element element : elements) {
			sb.append(element.toString());
		}
		sb.append("----Transitions: " + System.lineSeparator());
		for (Transition transition : transitions) {
			sb.append(transition.toString());
		}
		return sb.toString();
	}

}
