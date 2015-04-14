package de.muenchen.selenipo.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.Selector;
import de.muenchen.selenipo.Transition;

public class TransitionFx extends ElementFx implements Transition {

	private ObjectProperty<PoGenericFx> destination;

	public TransitionFx(String identefier, Selector type, String locator,
			PoGenericFx to) {
		super(identefier, type, locator);
		this.destination = new SimpleObjectProperty<PoGenericFx>(to);
	}

	@Override
	public PoGeneric getDestination() {
		return destination.get();
	}

	public void setDestination(PoGenericFx poGenericFx) {
		this.destination.set(poGenericFx);
	}

	public ObjectProperty<PoGenericFx> destinationProperty() {
		return destination;
	}

}
