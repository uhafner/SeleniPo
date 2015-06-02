
package de.muenchen.selenipo.po.base;

import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.Control;
import de.muenchen.selenipo.po.PageObject;
import de.muenchen.selenipo.po.base.OverviewPage;

public class WelcomePageGenerated extends BasePo {

	public final Control aENTER = control(by.link("bEnter"));

	public WelcomePageGenerated(PageObject po) {
		super(po);
	}
 
	
	public OverviewPage clickAENTER() {
		aENTER.click();
		return new OverviewPage(this);
	}
	
 
	/**
	 * Get the Control for the Transition aENTER.
	 * @return aENTER - Transition
	 */
	public Control getAENTER() {
		return aENTER;
	}
	

}
