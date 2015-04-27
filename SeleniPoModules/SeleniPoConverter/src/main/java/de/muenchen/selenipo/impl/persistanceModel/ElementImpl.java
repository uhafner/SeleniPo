package de.muenchen.selenipo.impl.persistanceModel;

import de.muenchen.selenipo.Element;
import de.muenchen.selenipo.Selector;

public class ElementImpl implements Element {

	private Selector type;
	private String locator;
	private String identefier;

	public ElementImpl(String idetefier, Selector type, String locator) {
		super();
		this.identefier = idetefier;
		this.type = type;
		this.locator = locator;
	}

	public ElementImpl() {
		super();
	}

	public Selector getType() {
		return type;
	}

	public void setType(Selector type) {
		this.type = type;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public String getIdentifier() {
		return identefier;
	}

	public void setIdentifier(String identefier) {
		this.identefier = identefier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identefier == null) ? 0 : identefier.hashCode());
		result = prime * result + ((locator == null) ? 0 : locator.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementImpl other = (ElementImpl) obj;
		if (identefier == null) {
			if (other.identefier != null)
				return false;
		} else if (!identefier.equals(other.identefier))
			return false;
		if (locator == null) {
			if (other.locator != null)
				return false;
		} else if (!locator.equals(other.locator))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "------ Element [type=" + type + ", locator=" + locator + "]"
				+ System.lineSeparator();
	}

}
