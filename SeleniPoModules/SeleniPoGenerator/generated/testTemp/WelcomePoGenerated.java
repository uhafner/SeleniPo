
package de.muenchen.selenipo.po.generated.testTemp;

import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.Control;
import de.muenchen.selenipo.po.PageObject;
import de.muenchen.selenipo.po.edit.testTemp.listPo;

public class WelcomePoGenerated extends BasePo {

	public final Control identefierHi = control(by.xpath("//h1"));
	public final Control enterButton = control(by.link("bEnter"));

	public WelcomePoGenerated(PageObject po) {
		super(po);
	}
 
	
	public listPo clickEnterButton() {
		return new listPo(this);
	}
	
 
	/**
	 * Get the Control for the Element $transition.getIdentifier().
	 * @return $transition.getIdentifier() - Element
	 */
	public Control getIdentefierHi() {
		return identefierHi;
	}
	
	/**
	 * Get the Control for the Transition enterButton.
	 * @return enterButton - Transition
	 */
	public Control getEnterButton() {
		return enterButton;
	}
	

}
