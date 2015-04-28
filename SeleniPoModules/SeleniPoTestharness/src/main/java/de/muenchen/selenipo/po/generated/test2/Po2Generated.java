
package de.muenchen.selenipo.po.generated.test2;

import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.Control;
import de.muenchen.selenipo.po.PageObject;
import de.muenchen.selenipo.po.edit.test.Po1;

public class Po2Generated extends BasePo {

	public final Control fg = control(by.radioButton("hgdh"));
	public final Control hd = control(by.xpath("dh"));
	public final Control hdhd = control(by.href("hdfgh"));

	public Po2Generated(PageObject po) {
		super(po);
	}
 
	
	public Po1 clickHd() {
		return new Po1(this);
	}
	
	
	public Po1 clickHdhd() {
		return new Po1(this);
	}
	
 
	/**
	 * Get the Control for the Element $transition.getIdentifier().
	 * @return $transition.getIdentifier() - Element
	 */
	public Control getFg() {
		return fg;
	}
	
	/**
	 * Get the Control for the Transition hd.
	 * @return hd - Transition
	 */
	public Control getHd() {
		return hd;
	}
	
	/**
	 * Get the Control for the Transition hdhd.
	 * @return hdhd - Transition
	 */
	public Control getHdhd() {
		return hdhd;
	}
	

}
