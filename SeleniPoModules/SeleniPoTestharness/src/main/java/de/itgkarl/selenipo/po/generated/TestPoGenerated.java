package de.itgkarl.selenipo.po.generated;

import de.itgkarl.selenipo.po.BasePo;
import de.itgkarl.selenipo.po.Control;
import de.itgkarl.selenipo.po.PageObject;

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
