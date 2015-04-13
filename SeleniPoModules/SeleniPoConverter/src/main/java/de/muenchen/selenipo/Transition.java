package de.muenchen.selenipo;

public class Transition {

	private PoGeneric from;
	private PoGeneric to;

	public Transition(PoGeneric from, PoGeneric to) {
		super();
		this.from = from;
		this.to = to;
	}

	public PoGeneric getFrom() {
		return from;
	}

	public void setFrom(PoGeneric from) {
		this.from = from;
	}

	public PoGeneric getTo() {
		return to;
	}

	public void setTo(PoGeneric to) {
		this.to = to;
	}

}
