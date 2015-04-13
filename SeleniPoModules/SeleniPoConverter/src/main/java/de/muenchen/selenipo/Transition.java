package de.muenchen.selenipo;

public class Transition extends Element {

	private PoGeneric to;

	public Transition(Selector type, String locator, PoGeneric to) {
		super(type, locator);
		this.to = to;
	}

	public PoGeneric getTo() {
		return to;
	}

	public void setTo(PoGeneric to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "------ Transition [to=" + to.getIdentifier() + ", type="
				+ super.getType() + ", locator=" + super.getLocator() + "]"
				+ System.lineSeparator();
	}

}
