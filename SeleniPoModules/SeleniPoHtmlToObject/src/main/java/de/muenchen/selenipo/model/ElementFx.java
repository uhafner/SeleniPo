package de.muenchen.selenipo.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import de.muenchen.selenipo.Element;
import de.muenchen.selenipo.Selector;

public class ElementFx implements Element {

	private ObjectProperty<Selector> type;
	private StringProperty locator;
	private StringProperty identefier;

	public ElementFx(String identefier,Selector type, String locator) {
		super();
		this.type = new SimpleObjectProperty<Selector>(type);
		this.locator = new SimpleStringProperty(locator);
		this.identefier = new SimpleStringProperty(identefier);
	}

	@Override
	public Selector getType() {
		return type.get();
	}

	@Override
	public void setType(Selector type) {
		this.type.set(type);
	}

	public ObjectProperty<Selector> typeProperty() {
		return type;
	}

	@Override
	public String getLocator() {
		return this.locator.get();
	}

	@Override
	public void setLocator(String locator) {
		this.locator.set(locator);

	}

	public StringProperty locatorProperty() {
		return locator;
	}

	@Override
	public String getIdentefier() {
		return this.identefier.get();
	}

	@Override
	public void setIdentefier(String identefier) {
		this.identefier.set(identefier);
	}

	public StringProperty identefierProperty() {
		return identefier;
	}

}
