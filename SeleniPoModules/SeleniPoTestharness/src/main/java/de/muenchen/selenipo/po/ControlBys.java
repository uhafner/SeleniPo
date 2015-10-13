/*
 * Projekt: KiTa Gebühren
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2014
 */
package de.muenchen.selenipo.po;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * @author IT@M/A23 Matthias Karl
 */
public class ControlBys implements Control {

	private final PageObject parent;
	private final By[] bys;

	/**
	 * @param driver
	 */
	public ControlBys(PageObject parent, By... bys) {
		this.parent = parent;
		this.bys = bys;
	}

	public WebElement resolve() {
		NoSuchElementException problem = new NoSuchElementException(
				"No 'By' specified!");
		for (By by : bys) {
			try {
				return parent.find(by);
			} catch (NoSuchElementException e) {
				problem = e;
			}
		}
		throw problem;
	}

	public void sendKeys(String t) {
		resolve().sendKeys(t);
	}

	public void uncheck() {
		check(resolve(), false);
	}

	public void check() {
		check(resolve(), true);
	}

	public void check(boolean state) {
		check(resolve(), state);
	}

	/**
	 * Sets the state of the checkbox to the specified value.
	 */
	protected void check(WebElement e, boolean state) {
		if (e.isSelected() != state)
			e.click();
	}

	public void click() {
		resolve().click();
	}

	public void set(String text) {
		WebElement e = resolve();
		e.clear();
		e.sendKeys(text);
	}

	public void set(Object text) {
		set(text.toString());
	}

	/**
	 * Select an option.
	 */
	public void select(String option) {
		WebElement e = resolve();
		e.findElement(parent.by.option(option)).click();

		// move the focus away from the select control to fire onchange event
		e.sendKeys(Keys.TAB);
	}

	/**
	 * @return value des Elements
	 */
	public String value() {
		WebElement e = resolve();
		return e.getAttribute("value");
	}

	/**
	 * @return true, wenn das Attribut "readonly" auf Wert "readonly" oder
	 *         "true" ist
	 */
	public boolean isReadOnly() {
		WebElement e = resolve();
		String attribute = e.getAttribute("readonly");
		if (attribute == null) {
			return false;
		} else if (attribute.equalsIgnoreCase("true")
				|| attribute.equalsIgnoreCase("readonly")) {
			return true;
		} else {
			throw new RuntimeException(
					"Control.isReadOnly() -- Unbekannter readonly-Attribut-Wert: "
							+ attribute);
		}
	}
}
