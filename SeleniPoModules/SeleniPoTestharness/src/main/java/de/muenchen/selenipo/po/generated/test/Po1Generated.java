
package de.muenchen.selenipo.po.generated.test;

import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.Control;
import de.muenchen.selenipo.po.PageObject;
import de.muenchen.selenipo.po.edit.test.Po1;

public class Po1Generated extends BasePo {

	public final Control dad = control(by.link("da"));
	public final Control da = control(by.href("da"));

	public Po1Generated(PageObject po) {
		super(po);
	}
 
	
	public Po1 clickDa() {
		return new Po1(this);
	}
	
 
	/**
	 * Get the Control for the Element $transition.getIdentifier().
	 * @return $transition.getIdentifier() - Element
	 */
	public Control getDad() {
		return dad;
	}
	
	/**
	 * Get the Control for the Transition da.
	 * @return da - Transition
	 */
	public Control getDa() {
		return da;
	}
	

}
