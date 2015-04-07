/*
 * Projekt: KiTa Gebühren
 * Autor: $Id$
 * Copyright (c): IT@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2012
 */
package de.itgkarl.SeleniPoTestharness.po;

import org.openqa.selenium.WebElement;

/**
 * @author sybille.mueller
 */
public interface Control {

	WebElement resolve();

	void sendKeys(String t);

	void uncheck();

	void check();

	void check(boolean state);

	void click();

	void set(String text);

	void set(Object text);

	void select(String option);

	String value();

	boolean isReadOnly();
}
