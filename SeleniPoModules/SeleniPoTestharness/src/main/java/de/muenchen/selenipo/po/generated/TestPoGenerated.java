package de.muenchen.selenipo.po.generated;

import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.Control;
import de.muenchen.selenipo.po.PageObject;

public class TestPoGenerated extends BasePo {

	public final Control bEnter = control(by.link("bEnter"));

	/**
	 * Constructor which sets fields of the Parent PageObject.
	 * 
	 * @param po
	 *            some pageObject
	 */
	public TestPoGenerated(final PageObject po) {
		super(po);
	}
}
