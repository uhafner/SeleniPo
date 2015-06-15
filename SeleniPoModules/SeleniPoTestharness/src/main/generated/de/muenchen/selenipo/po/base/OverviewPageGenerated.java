
package de.muenchen.selenipo.po.base;

import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.Control;
import de.muenchen.selenipo.po.PageObject;
import de.muenchen.selenipo.po.base.WelcomePage;

public class OverviewPageGenerated extends BasePo {

	public final Control aToDoApp = control(by.link("ToDo-App"));

	public OverviewPageGenerated(PageObject po) {
		super(po);
	}
 
	
	public WelcomePage clickAToDoApp() {
		aToDoApp.click();
		return new WelcomePage(this);
	}
	
 
	/**
	 * Get the Control for the Transition aToDoApp.
	 * @return aToDoApp - Transition
	 */
	public Control getAToDoApp() {
		return aToDoApp;
	}
	

}
